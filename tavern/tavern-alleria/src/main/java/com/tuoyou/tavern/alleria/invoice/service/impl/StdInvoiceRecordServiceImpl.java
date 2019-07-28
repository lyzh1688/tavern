package com.tuoyou.tavern.alleria.invoice.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.alleria.configuration.TTLContext;
import com.tuoyou.tavern.alleria.invoice.dao.StdInvoiceRecordMapper;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.TaxScanResultService;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.invoice.common.libs.excel.ExcelListener;
import com.tuoyou.tavern.invoice.ocr.libs.client.TBOcrAgent;
import com.tuoyou.tavern.invoice.ocr.libs.model.TBInvoiceModel;
import com.tuoyou.tavern.invoice.verify.libs.client.ZBJVerifyAgent;
import com.tuoyou.tavern.invoice.verify.libs.model.InvoiceKeyModel;
import com.tuoyou.tavern.invoice.verify.libs.model.ZBJInvoiceData;
import com.tuoyou.tavern.invoice.verify.libs.model.ZBJVerifyResult;
import com.tuoyou.tavern.protocol.alleria.common.FileUploadStatus;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.file.InvoiceExcel;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecordVO;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Slf4j
@Service
public class StdInvoiceRecordServiceImpl extends ServiceImpl<StdInvoiceRecordMapper, StdInvoiceRecord> implements StdInvoiceRecordService {

    @Autowired
    private ZBJVerifyAgent verifyAgent;
    @Autowired
    private TBOcrAgent ocrAgent;
    @Autowired
    private TaxScanResultService taxScanResultService;
    @Autowired
    private StdInvoiceDtlRecordService stdInvoiceDtlRecordService;
    @Autowired
    private TTLContext ttlContext;
    @Value("${invoice.zzs.host:http://101.132.134.96:80/invoice/zzs/}")
    private String imageUrlHost;
    @Value("${invoice.zzs.dir:\\mnt\\file\\zzs\\}")
    private String zzsDir;

    @Override
    public void updateStatus(String batchId, String valid) {
        this.baseMapper.updateFileStatus(batchId, valid);
    }

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
            TaxScanResult taxScanResult = new TaxScanResult();
            StdInvoiceRecord stdInvoiceRecord = null;
            StdInvoiceDtlRecord stdInvoiceDtlRecord = null;
            try {

                //ocr每次都要查询
                TBInvoiceModel tbInvoiceModel = this.ocrAgent.doOcr(file.getPath());
                String fileId = StringUtils.join(tbInvoiceModel.getInvoiceCode(), "_", tbInvoiceModel.getInvoiceId());
                //查询是否已执行
                TaxScanResult scanResult = this.taxScanResultService.getById(fileId);
                if (scanResult != null && scanResult.getIsValid().equals("1")) {
                    //说明已经执行过此发票查询
                    //修改batchId
                    scanResult.setBatchId(batchId);
                    stdInvoiceRecord = new StdInvoiceRecord();
                    stdInvoiceRecord.setFileId(fileId);
                    stdInvoiceRecord.setBatchId(batchId);
                    this.taxScanResultService.updateById(scanResult);
                    this.baseMapper.updateById(stdInvoiceRecord);
                    continue;
                }

                InvoiceKeyModel keyModel = new InvoiceKeyModel();
                keyModel.setInvoiceCode(tbInvoiceModel.getInvoiceCode());
                keyModel.setInvoiceId(tbInvoiceModel.getInvoiceId());
                keyModel.setInvoiceDate(tbInvoiceModel.getInvoiceDate());
                keyModel.setTaxFreeAmount(tbInvoiceModel.getTaxFreeAmount());
                keyModel.setCheckCode(tbInvoiceModel.getCheckCode());
                ZBJVerifyResult result = this.verifyAgent.doVerify(keyModel);
                ZBJInvoiceData invoiceData = result.getInvoiceData();
                taxScanResult.setFileId(fileId);
                taxScanResult.setFileName(file.getName());
                taxScanResult.setIsValid("1");
                taxScanResult.setIsVerified("1");
                taxScanResult.setIsEmend("0");
                taxScanResult.setInvoiceId(tbInvoiceModel.getInvoiceId());
                taxScanResult.setInvoiceCode(tbInvoiceModel.getInvoiceCode());
                taxScanResult.setInvoiceDate(tbInvoiceModel.getInvoiceDate());
                taxScanResult.setCheckCode(tbInvoiceModel.getCheckCode());
                taxScanResult.setTaxFreeAmount(tbInvoiceModel.getTaxFreeAmount());
                taxScanResult.setUpdateDate(LocalDateTime.now());
                taxScanResult.setBatchId(batchId);
                taxScanResult.setOperator("admin");
                stdInvoiceRecord = new StdInvoiceRecord();
                stdInvoiceRecord.setFileId(fileId);
                stdInvoiceRecord.setBatchId(batchId);
                stdInvoiceRecord.setFileName(file.getName());
                stdInvoiceRecord.setIsValid("1");
                stdInvoiceRecord.setUpdateDate(LocalDateTime.now());
                stdInvoiceRecord.setCustomId(invoiceData != null ? invoiceData.getBuyerID() : null);
                stdInvoiceDtlRecord = new StdInvoiceDtlRecord();
                stdInvoiceDtlRecord.setFileId(fileId);
                stdInvoiceDtlRecord.setInvoiceId(invoiceData.getInvoiceId());
                stdInvoiceDtlRecord.setInvoiceCode(invoiceData.getInvoiceCode());
                String path = StringUtils.substringAfterLast(file.getPath(), zzsDir);
                String imageUrl = StringUtils.join(imageUrlHost, StringUtils.replacePattern(path, "\\\\", "/"));
                stdInvoiceDtlRecord.setImageUrl(imageUrl);
                if (result.getSuccess()) {
                    stdInvoiceDtlRecord.setBuyerName(invoiceData.getBuyerName());
                    stdInvoiceDtlRecord.setBuyerTaxCode(invoiceData.getBuyerID());
                    stdInvoiceDtlRecord.setBankBranch(invoiceData.getBuyerBank());
                    stdInvoiceDtlRecord.setBankAccount(invoiceData.getBuyerBank());
                    stdInvoiceDtlRecord.setAddress(StringUtils.substringBeforeLast(invoiceData.getBuyerContact(), " "));
                    stdInvoiceDtlRecord.setTel(StringUtils.substringAfterLast(invoiceData.getBuyerContact(), " ") == "" ? invoiceData.getBuyerContact() : StringUtils.substringAfterLast(invoiceData.getBuyerContact(), " "));
                    stdInvoiceDtlRecord.setInvoiceDate(DateUtils.parseDateTime(StringUtils.join(invoiceData.getInvoiceDate(), "-00:00:00"), DateUtils.FIX_DATETIME_FORMATTER));
                    stdInvoiceDtlRecord.setProductVersion(invoiceData.getInvoiceType());
                    stdInvoiceDtlRecord.setReceiptId(invoiceData.getInvoiceMachineNum());
                    stdInvoiceDtlRecord.setProductName(invoiceData.getItems().isEmpty() ? null : invoiceData.getItems().get(0).getName());
                    stdInvoiceDtlRecord.setProductSpec(invoiceData.getItems().isEmpty() ? null : invoiceData.getItems().get(0).getSpec());
                    stdInvoiceDtlRecord.setProductUnit(invoiceData.getItems().isEmpty() ? null : invoiceData.getItems().get(0).getUnit());
                    stdInvoiceDtlRecord.setProductCount(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getAmount()));
                    stdInvoiceDtlRecord.setProductUnitPrice(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getPriceUnit()));
                    stdInvoiceDtlRecord.setTotalPrice(new BigDecimal(invoiceData.getTaxAmount()));
                    stdInvoiceDtlRecord.setTaxRate(invoiceData.getItems().get(0).getTaxRate());
                    stdInvoiceDtlRecord.setTax(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getTaxSum()));
                    stdInvoiceDtlRecord.setTaxTypeCode(invoiceData.getInvoiceType());
                } else {
                    taxScanResult.setIsValid("0");
                    taxScanResult.setIsVerified("0");
                    taxScanResult.setFailedReason(result.getDesc());
                }
                this.taxScanResultService.saveOrUpdate(taxScanResult);
                this.saveOrUpdate(stdInvoiceRecord);
                this.stdInvoiceDtlRecordService.saveOrUpdate(stdInvoiceDtlRecord);

            } catch (Exception e) {
                taxScanResult.setIsValid("0");
                taxScanResult.setIsVerified("0");
                taxScanResult.setFailedReason(e.getMessage());
                this.taxScanResultService.saveOrUpdate(taxScanResult);

            }
            JSONObject fileUploadObject = new JSONObject();
            fileUploadObject.put("status", FileUploadStatus.IN_PROCESS);
            double percentage = new BigDecimal((i + 1))
                    .divide(new BigDecimal(files.length), 2, BigDecimal.ROUND_HALF_EVEN)
                    .multiply(new BigDecimal(80))
                    .add(new BigDecimal(20)).doubleValue();
            fileUploadObject.put("percentage", percentage);
            ttlContext.putValue(batchId, fileUploadObject);
            log.info("batchId: {} file complete: {}", batchId, percentage);
        }
    }

    @Override
    public void parseStdInvoice(FileTransfer fileTransfer) {
        String destLocation = fileTransfer.getDestLocation();
        String batchId = fileTransfer.getBatchId();
        File[] files = new File(destLocation).listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            List<InvoiceExcel> invoiceExcelList;
            ExcelListener<InvoiceExcel> listener = new ExcelListener<InvoiceExcel>();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String pattern = StringUtils.substringAfterLast(file.getName(), ".");
            if (pattern.equals("xls")) {
                (new ExcelReader(inputStream, ExcelTypeEnum.XLS, (Object) null, listener)).read(new Sheet(1, 1, InvoiceExcel.class));
            } else {
                (new ExcelReader(inputStream, ExcelTypeEnum.XLSX, (Object) null, listener)).read(new Sheet(1, 1, InvoiceExcel.class));
            }
            invoiceExcelList = listener.getDatas();

            //10条一组
            List<List<InvoiceExcel>> partList = Lists.partition(invoiceExcelList, 10);
            for (int j = 0; j < partList.size(); j++) {
                List<InvoiceExcel> list = partList.get(j).stream().filter(invoiceExcel -> StringUtils.isNoneEmpty(invoiceExcel.getInvoiceCode(), invoiceExcel.getInvoiceId())).collect(Collectors.toList());
                List<StdInvoiceRecord> stdInvoiceRecordList =
                        list.stream()
                                .map(dtl -> {
                                    StdInvoiceRecord stdInvoiceRecord = new StdInvoiceRecord();
                                    stdInvoiceRecord.setFileId(StringUtils.join(dtl.getInvoiceCode(), "_", dtl.getInvoiceId()));
                                    stdInvoiceRecord.setBatchId(batchId);
                                    stdInvoiceRecord.setFileName(file.getName());
                                    stdInvoiceRecord.setIsValid("1");
                                    stdInvoiceRecord.setUpdateDate(LocalDateTime.now());
                                    stdInvoiceRecord.setCustomId(dtl.getBuyerTaxCode());
                                    return stdInvoiceRecord;
                                }).collect(Collectors.toList());
                List<StdInvoiceDtlRecord> stdInvoiceDtlRecordList = list.stream()
                        .map(dtl -> {
                            StdInvoiceDtlRecord stdInvoiceDtlRecord = new StdInvoiceDtlRecord();
                            BeanUtils.copyProperties(dtl, stdInvoiceDtlRecord);
                            stdInvoiceDtlRecord.setFileId(StringUtils.join(dtl.getInvoiceCode(), "_", dtl.getInvoiceId()));
                            stdInvoiceDtlRecord.setBankBranch(dtl.getBankAccount());
                            stdInvoiceDtlRecord.setAddress(dtl.getTel());
                            stdInvoiceDtlRecord.setTax(StringUtils.isEmpty(dtl.getTax()) ? null : new BigDecimal(dtl.getTax()));
                            stdInvoiceDtlRecord.setTotalPrice(StringUtils.isEmpty(dtl.getTotalPrice()) ? null : new BigDecimal(dtl.getTotalPrice()));
                            stdInvoiceDtlRecord.setInvoiceDate(StringUtils.isEmpty(dtl.getInvoiceDate()) ? null : DateUtils.parseDateTime(StringUtils.join(dtl.getInvoiceDate(), " 00:00:00"), DateUtils.DEFAULT_DATETIME_FORMATTER));
                            return stdInvoiceDtlRecord;
                        }).collect(Collectors.toList());
                this.saveOrUpdateBatch(stdInvoiceRecordList);
                this.stdInvoiceDtlRecordService.saveOrUpdateBatch(stdInvoiceDtlRecordList);
            }
            JSONObject fileUploadObject = new JSONObject();
            fileUploadObject.put("status", FileUploadStatus.IN_PROCESS);
            double percentage = new BigDecimal((i + 1))
                    .divide(new BigDecimal(files.length), 2, BigDecimal.ROUND_HALF_EVEN)
                    .multiply(new BigDecimal(80))
                    .add(new BigDecimal(20)).doubleValue();
            fileUploadObject.put("percentage", percentage);
            ttlContext.putValue(batchId, fileUploadObject);
            log.info("batchId: {} file complete: {}", batchId, percentage);
        }
    }

    @Override
    public IPage<StdInvoiceRecordVO> getStdInvoiceRecord(Page page, StdInvoiceRecordDTO stdInvoiceRecordDTO) {
        IPage<StdInvoiceRecord> stdInvoiceRecordIPage = this.baseMapper.selectStdInvoiceRecord(page, stdInvoiceRecordDTO);
//        IPage<StdInvoiceRecord> stdInvoiceRecordIPage = this.page(page, Wrappers.<StdInvoiceRecord>query().lambda()
//                .eq(StdInvoiceRecord::getIsValid, "1"));
        List<StdInvoiceRecordVO> stdInvoiceRecordVOList = stdInvoiceRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    StdInvoiceRecordVO stdInvoiceRecordVO = new StdInvoiceRecordVO();
                    BeanUtils.copyProperties(record, stdInvoiceRecordVO);
                    stdInvoiceRecordVO.setAccountPeriod(DateUtils.formatDateTime(record.getAccountPeriod(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    stdInvoiceRecordVO.setUpdateDate(DateUtils.formatDateTime(record.getUpdateDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    return stdInvoiceRecordVO;
                }).collect(Collectors.toList());
        Page<StdInvoiceRecordVO> stdInvoiceRecordVOPage = new Page<>();
        stdInvoiceRecordVOPage.setRecords(stdInvoiceRecordVOList);
        stdInvoiceRecordVOPage.setCurrent(stdInvoiceRecordIPage.getCurrent());
        stdInvoiceRecordVOPage.setSize(stdInvoiceRecordIPage.getSize());
        stdInvoiceRecordVOPage.setCurrent(stdInvoiceRecordIPage.getCurrent());
        stdInvoiceRecordVOPage.setTotal(stdInvoiceRecordIPage.getTotal());
        return stdInvoiceRecordVOPage;
    }

    public static void main(String[] args) {
        String a = StringUtils.join("127.0.0.1:80/invoice/zzs/");
        String s = StringUtils.replacePattern(a + "fs\\\\sfa\\\\\fsfsd\\\\sfdsa", "\\\\", "/");
        System.out.println(s);
        double percentage = new BigDecimal((0 + 1))
                .divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_EVEN)
                .multiply(new BigDecimal(80))
                .add(new BigDecimal(20)).doubleValue();
        System.out.println(percentage);

        String s1 = StringUtils.substringAfterLast("\\\\mnt\\\\quote\\\\20190720\\\\201907201935\\\\2018_09_19_14_15_19.jpg", "\\\\mnt\\\\quote\\\\");
        String imageUrl = StringUtils.join("127.0.0.1:80/invoice/zzs/", StringUtils.replacePattern(s1, "\\\\", "/"));
        System.out.println(s1);
        System.out.println(imageUrl);

    }
}
