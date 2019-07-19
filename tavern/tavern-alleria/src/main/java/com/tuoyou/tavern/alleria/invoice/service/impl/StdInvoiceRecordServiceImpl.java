package com.tuoyou.tavern.alleria.invoice.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.invoice.dao.StdInvoiceRecordMapper;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.TaxScanResultService;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.invoice.ocr.libs.client.TBOcrAgent;
import com.tuoyou.tavern.invoice.ocr.libs.model.TBInvoiceModel;
import com.tuoyou.tavern.invoice.verify.libs.client.ZBJVerifyAgent;
import com.tuoyou.tavern.invoice.verify.libs.model.InvoiceKeyModel;
import com.tuoyou.tavern.invoice.verify.libs.model.ZBJInvoiceData;
import com.tuoyou.tavern.invoice.verify.libs.model.ZBJVerifyResult;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Slf4j
@Service
@AllArgsConstructor
public class StdInvoiceRecordServiceImpl extends ServiceImpl<StdInvoiceRecordMapper, StdInvoiceRecord> implements StdInvoiceRecordService {

    private final ZBJVerifyAgent verifyAgent;
    private final TBOcrAgent ocrAgent;
    private final TaxScanResultService taxScanResultService;
    private final StdInvoiceDtlRecordService stdInvoiceDtlRecordService;

    @Override
    public void updateStatus(String fileId, String valid) {
        this.baseMapper.updateFileStatus(fileId, valid);
    }

    @Override
    public void parseZzsInvoice(FileTransfer fileTransfer, HttpSession httpSession) {
        //1.scan记录
        //2.ocr
        //3.verify
        //4.record
        //5.dtl
        String destLocation = fileTransfer.getDestLocation();
        String batchId = fileTransfer.getBatchId();
        File[] files = new File(destLocation).listFiles();
        for (int i = 0; i < 5; i++) {
//            File file = files[i];
            TaxScanResult taxScanResult = new TaxScanResult();
            StdInvoiceRecord stdInvoiceRecord = null;
            StdInvoiceDtlRecord stdInvoiceDtlRecord = null;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           /* try {
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
                if (result.getSuccess()) {
                    stdInvoiceDtlRecord = new StdInvoiceDtlRecord();
                    stdInvoiceDtlRecord.setFileId(fileId);
                    stdInvoiceDtlRecord.setInvoiceId(invoiceData.getInvoiceId());
                    stdInvoiceDtlRecord.setInvoiceCode(invoiceData.getInvoiceCode());
                    stdInvoiceDtlRecord.setBuyerName(invoiceData.getBuyerName());
                    stdInvoiceDtlRecord.setBuyerTaxCode(invoiceData.getBuyerID());
                    stdInvoiceDtlRecord.setBankBranch(invoiceData.getBuyerBank());
                    stdInvoiceDtlRecord.setAddress(StringUtils.substringBeforeLast(invoiceData.getBuyerContact(), " "));
                    stdInvoiceDtlRecord.setTel(StringUtils.substringAfterLast(invoiceData.getBuyerContact(), " "));
                    stdInvoiceDtlRecord.setInvoiceDate(DateUtils.parseDateTime(StringUtils.join(invoiceData.getInvoiceDate(), "-00:00:00"), DateUtils.FIX_DATETIME_FORMATTER));
                    stdInvoiceDtlRecord.setProductVersion(invoiceData.getInvoiceType());
                    stdInvoiceDtlRecord.setReceiptId(invoiceData.getInvoiceMachineNum());
                    stdInvoiceDtlRecord.setProductName(invoiceData.getItems().isEmpty() ? null : invoiceData.getItems().get(0).getName());
                    stdInvoiceDtlRecord.setProductSpec(invoiceData.getItems().isEmpty() ? null : invoiceData.getItems().get(0).getSpec());
                    stdInvoiceDtlRecord.setProductUnit(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getUnit()));
                    stdInvoiceDtlRecord.setProductCount(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getAmount()));
                    stdInvoiceDtlRecord.setProductUnitPrice(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getPriceUnit()));
                    stdInvoiceDtlRecord.setTotalPrice(new BigDecimal(invoiceData.getTaxAmount()));
                    stdInvoiceDtlRecord.setTaxRate(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getTaxRate()));
                    stdInvoiceDtlRecord.setTax(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getTaxSum()));
                    stdInvoiceDtlRecord.setTaxTypeCode(invoiceData.getInvoiceType());
                    this.stdInvoiceDtlRecordService.saveOrUpdate(stdInvoiceDtlRecord);
                } else {
                    taxScanResult.setIsValid("0");
                    taxScanResult.setIsVerified("0");
                    taxScanResult.setFailedReason(result.getDesc());
                }
                this.taxScanResultService.saveOrUpdate(taxScanResult);
                this.saveOrUpdate(stdInvoiceRecord);
            } catch (Exception e) {
                taxScanResult.setIsValid("0");
                taxScanResult.setIsVerified("0");
                taxScanResult.setFailedReason(e.getMessage());
                this.taxScanResultService.saveOrUpdate(taxScanResult);
            }*/
            httpSession.setAttribute(batchId, new BigDecimal((i + 1))
                    .divide(new BigDecimal(5), 2, BigDecimal.ROUND_HALF_EVEN)
                    .multiply(new BigDecimal(80))
                    .add(new BigDecimal(20)));

            BigDecimal bigDecimal = (BigDecimal) httpSession.getAttribute(batchId);
            log.info("batchId: {} pt: {}",batchId,bigDecimal.toPlainString());

        }


    }
}
