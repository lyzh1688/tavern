package com.tuoyou.tavern.alleria.zzs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.alleria.configuration.TTLContext;
import com.tuoyou.tavern.alleria.util.CommonUtils;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.alleria.zzs.dao.ZzsTaxScanResultMapper;
import com.tuoyou.tavern.alleria.zzs.service.ZzsTaxScanItemsResultService;
import com.tuoyou.tavern.alleria.zzs.service.ZzsTaxScanResultService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.invoice.ocr.libs.client.TBOcrAgent;
import com.tuoyou.tavern.invoice.ocr.libs.model.TBInvoiceModel;
import com.tuoyou.tavern.invoice.verify.libs.client.ZBJVerifyAgent;
import com.tuoyou.tavern.invoice.verify.libs.model.InvoiceKeyModel;
import com.tuoyou.tavern.invoice.verify.libs.model.ZBJInvoiceData;
import com.tuoyou.tavern.invoice.verify.libs.model.ZBJVerifyResult;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.protocol.alleria.model.ZBJInvoiceItemVO;
import com.tuoyou.tavern.protocol.alleria.model.ZzsTaxScanItemsResult;
import com.tuoyou.tavern.protocol.alleria.model.ZzsTaxScanResult;
import com.tuoyou.tavern.protocol.alleria.model.ZzsTaxScanResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/03 <br>
 */
@Slf4j
@Service
public class ZzsTaxScanResultServiceImpl extends ServiceImpl<ZzsTaxScanResultMapper, ZzsTaxScanResult> implements ZzsTaxScanResultService {

    @Autowired
    private ZBJVerifyAgent verifyAgent;
    @Autowired
    private TBOcrAgent ocrAgent;
    @Autowired
    private ZzsTaxScanItemsResultService taxScanItemsResultService;
    @Autowired
    private TTLContext ttlContext;
    @Value("${invoice.zzs.host:http://127.0.0.1:80/invoice/zzs/}")
    private String imageUrlHost;
    @Value("${invoice.zzs.dir:\\mnt\\file\\zzs\\}")
    private String zzsDir;

    @Override
    public void parseZzsInvoice(FileTransfer fileTransfer) {
        //1.scan记录
        //2.ocr
        //3.verify
        //4.record
        //5.dtl
        String destLocation = fileTransfer.getDestLocation();
        String batchId = fileTransfer.getBatchId();
        File[] files = new File(destLocation).listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            ZzsTaxScanResult taxScanResult = new ZzsTaxScanResult();
            try {
                //ocr每次都要查询
                TBInvoiceModel tbInvoiceModel = this.ocrAgent.doOcr(file.getPath());
                String fileId = StringUtils.join(tbInvoiceModel.getInvoiceCode(), "_", tbInvoiceModel.getInvoiceId());
                //查询是否已执行
                ZzsTaxScanResult scanResult = this.getById(fileId);
                if (scanResult != null && scanResult.getIsValid().equals("1")) {
                    //说明已经执行过此发票查询
                    //修改batchId
                    scanResult.setBatchId(batchId);
                    this.updateById(scanResult);
                    continue;
                }
                taxScanResult.setFileId(fileId);
                taxScanResult.setFileName(file.getName());
                taxScanResult.setInvoiceId(tbInvoiceModel.getInvoiceId());
                taxScanResult.setInvoiceCode(tbInvoiceModel.getInvoiceCode());
                taxScanResult.setInvoiceDate(tbInvoiceModel.getInvoiceDate());
                taxScanResult.setCheckCode(tbInvoiceModel.getCheckCode());
                taxScanResult.setTaxFreeAmount(tbInvoiceModel.getTaxFreeAmount());
                taxScanResult.setIsValid("1");
                taxScanResult.setIsVerified("1");
                taxScanResult.setIsEmend("0");
                taxScanResult.setUpdateDate(LocalDateTime.now());
                taxScanResult.setBatchId(batchId);
                taxScanResult.setOperator("admin");
                taxScanResult.setFailedReason(null);
                InvoiceKeyModel keyModel = this.copyInvoiceKeyModel(tbInvoiceModel.getInvoiceCode(), tbInvoiceModel.getInvoiceId(), tbInvoiceModel.getInvoiceDate(), tbInvoiceModel.getTaxFreeAmount(), tbInvoiceModel.getCheckCode());
                ZBJVerifyResult result = this.verifyAgent.doVerify(keyModel);
                String path = StringUtils.substringAfterLast(file.getPath(), zzsDir);
                String imageUrl = StringUtils.join(imageUrlHost, StringUtils.replacePattern(path, "\\\\", "/"));
                taxScanResult.setImageUrl(imageUrl);
                this.updateTaxScanPropAfterZJBVerify(result, taxScanResult);
                this.saveOrUpdate(taxScanResult);
            } catch (Exception e) {
                log.error("ZzsTaxScanResultServiceImpl-->parseZzsInvoice-->Error: {}", e.getMessage());
                taxScanResult.setIsValid("0");
                taxScanResult.setIsVerified("0");
                taxScanResult.setFailedReason(e.getMessage());
                this.saveOrUpdate(taxScanResult);

            }
            JSONObject fileUploadObject = CommonUtils.percentageRecord(i, files.length);
            ttlContext.putValue(batchId, fileUploadObject);
            log.info("zzs invoice upload batchId: {} file complete: {}", batchId, fileUploadObject.getDouble("percentage"));
        }
    }

    @Override
    public IPage<ZzsTaxScanResultVO> getZzsTaxScanResult(Page page, TaxScanResultDTO taxScanResultDTO) {
        IPage<ZzsTaxScanResult> taxScanResultIPage = this.baseMapper.selectTaxScanResult(page, taxScanResultDTO);
        List<ZzsTaxScanResultVO> fileUploadRecordVOList = taxScanResultIPage.getRecords()
                .stream()
                .map(record -> {
                    ZzsTaxScanResultVO taxScanResultVO = new ZzsTaxScanResultVO();
                    BeanUtils.copyProperties(record, taxScanResultVO);
                    taxScanResultVO.setUpdateDate(DateUtils.formatDateTime(record.getUpdateDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    String goodData = record.getGoodsData();
                    List<ZBJInvoiceItemVO> zbjInvoiceItemVOList = StringUtils.isNoneEmpty(goodData) ? JSON.parseArray(goodData, ZBJInvoiceItemVO.class) : Lists.newArrayList();
                    taxScanResultVO.setGoodsData(zbjInvoiceItemVOList);
                    return taxScanResultVO;
                }).collect(Collectors.toList());
        return CommonUtils.newIPage(taxScanResultIPage, fileUploadRecordVOList);
    }

    @Override
    public ZzsTaxScanResultVO getTaxScanResultByFileId(String fileId) {
        ZzsTaxScanResult taxScanResult = this.getById(fileId);
        ZzsTaxScanResultVO taxScanResultVO = new ZzsTaxScanResultVO();
        BeanUtils.copyProperties(taxScanResult, taxScanResultVO);
        return taxScanResultVO;
    }

    @Transactional
    @Override
    public void updateZzsInvoiceDtl(ZZSInvoiceKeyField zzsInvoiceKeyField) throws Exception {
        //1. 删除older数据结果
        ZzsTaxScanResult taxScanResult = this.getById(zzsInvoiceKeyField.getFileId());
        taxScanResult.setIsValid("0");
        this.updateById(taxScanResult);
        //2. 新增数据
        ZzsTaxScanResult newTaxScanResult = new ZzsTaxScanResult();
        BeanUtils.copyProperties(taxScanResult, newTaxScanResult);
        BeanUtils.copyProperties(zzsInvoiceKeyField, newTaxScanResult);
        newTaxScanResult.setFileId(StringUtils.join(zzsInvoiceKeyField.getInvoiceCode(), "_", zzsInvoiceKeyField.getInvoiceId()));
        newTaxScanResult.setUpdateDate(LocalDateTime.now());
        newTaxScanResult.setIsValid("1");
        newTaxScanResult.setIsVerified("1");
        newTaxScanResult.setIsEmend("1");
        newTaxScanResult.setOperator("admin");
        InvoiceKeyModel keyModel = this.copyInvoiceKeyModel(zzsInvoiceKeyField.getInvoiceCode(), zzsInvoiceKeyField.getInvoiceId(), zzsInvoiceKeyField.getInvoiceDate(), zzsInvoiceKeyField.getTaxFreeAmount(), zzsInvoiceKeyField.getCheckCode());
        ZBJVerifyResult result = this.verifyAgent.doVerify(keyModel);
        this.updateTaxScanPropAfterZJBVerify(result, newTaxScanResult);
        this.saveOrUpdate(newTaxScanResult);
    }

    private InvoiceKeyModel copyInvoiceKeyModel(String invoiceCode, String invoiceId, String invoiceDate, String taxFreeAmount, String checkCode) {
        InvoiceKeyModel keyModel = new InvoiceKeyModel();
        keyModel.setInvoiceCode(invoiceCode);
        keyModel.setInvoiceId(invoiceId);
        keyModel.setInvoiceDate(invoiceDate);
        keyModel.setTaxFreeAmount(taxFreeAmount);
        keyModel.setCheckCode(checkCode);
        return keyModel;
    }

    private void updateTaxScanPropAfterZJBVerify(ZBJVerifyResult result, ZzsTaxScanResult taxScanResult) {
        JSONArray zzsTaxScanItemsResultArray = new JSONArray();
        if (result.getSuccess()) {
            ZBJInvoiceData invoiceData = result.getInvoiceData();
            taxScanResult.setInvoiceType(invoiceData.getInvoiceType());
            taxScanResult.setSellerName(invoiceData.getSellerName());
            taxScanResult.setSellerTaxCode(invoiceData.getSellerTaxCode());
            taxScanResult.setSellerBank(invoiceData.getSellerBank());
            taxScanResult.setBuyerName(invoiceData.getBuyerName());
            taxScanResult.setBuyerId(invoiceData.getBuyerID());
            taxScanResult.setBuyerContact(invoiceData.getBuyerContact());
            taxScanResult.setBuyerBank(invoiceData.getBuyerBank());
            taxScanResult.setInvoiceMachineNum(invoiceData.getInvoiceMachineNum());
            taxScanResult.setIsInvalid(invoiceData.getIsInVaild());
            taxScanResult.setTaxAmount(invoiceData.getTaxAmount());
            taxScanResult.setTotalAmount(invoiceData.getTotalAmount());
            taxScanResult.setQuantity(invoiceData.getQuantity());
            taxScanResult.setRemark(invoiceData.getRemark());
            taxScanResult.setVerifyTimes(invoiceData.getVerifyTimes());
            taxScanResult.setUpdateTime(invoiceData.getUpdateTime());
            if (!invoiceData.getItems().isEmpty()) {
                invoiceData.getItems()
                        .forEach(item -> {
                            JSONObject zzsTaxScanItemsResult = new JSONObject();
                            zzsTaxScanItemsResult.put("invoiceId", invoiceData.getInvoiceId());
                            zzsTaxScanItemsResult.put("invoiceCode", invoiceData.getInvoiceCode());
                            zzsTaxScanItemsResult.put("name", item.getName());
                            zzsTaxScanItemsResult.put("spec", item.getSpec());
                            zzsTaxScanItemsResult.put("unit", item.getUnit());
                            zzsTaxScanItemsResult.put("amount", item.getAmount());
                            zzsTaxScanItemsResult.put("priceUnit", item.getPriceUnit());
                            zzsTaxScanItemsResult.put("priceSum", item.getPriceSum());
                            zzsTaxScanItemsResult.put("taxRate", item.getTaxRate());
                            zzsTaxScanItemsResult.put("taxSum", item.getTaxSum());
                            zzsTaxScanItemsResultArray.add(zzsTaxScanItemsResult);
                        });
                taxScanResult.setGoodsData(zzsTaxScanItemsResultArray.toJSONString());
            }
        } else {
            taxScanResult.setIsValid("0");
            taxScanResult.setIsVerified("0");
            taxScanResult.setFailedReason(result.getDesc());
        }
    }
}
