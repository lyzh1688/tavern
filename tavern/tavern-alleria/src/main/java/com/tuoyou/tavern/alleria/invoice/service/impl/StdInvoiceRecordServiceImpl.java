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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
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
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            TaxScanResult taxScanResult = TaxScanResult.builder().build();
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
                    stdInvoiceRecord = StdInvoiceRecord.builder()
                            .fileId(fileId)
                            .batchId(batchId)
                            .build();
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
                taxScanResult = TaxScanResult.builder()
                        .fileId(fileId)
                        .fileName(file.getName())
                        .isValid("1")
                        .isVerified("1")
                        .invoiceId(tbInvoiceModel.getInvoiceId())
                        .invoiceCode(tbInvoiceModel.getInvoiceCode())
                        .invoiceDate(tbInvoiceModel.getInvoiceDate())
                        .checkCode(tbInvoiceModel.getCheckCode())
                        .taxFreeAmount(tbInvoiceModel.getTaxFreeAmount())
                        .updateDate(LocalDateTime.now())
                        .batchId(batchId)
                        .operator("admin")
                        .build();
                stdInvoiceRecord = StdInvoiceRecord.builder()
                        .fileId(fileId)
                        .batchId(batchId)
                        .fileName(file.getName())
                        .isValid("1")
                        .updateDate(LocalDateTime.now())
                        .customId(invoiceData.getBuyerID())
                        .build();
                if (result.getSuccess()) {
                    stdInvoiceDtlRecord = StdInvoiceDtlRecord.builder()
                            .fileId(fileId)
                            .invoiceId(invoiceData.getInvoiceId())
                            .invoiceCode(invoiceData.getInvoiceCode())
                            .buyerName(invoiceData.getBuyerName())
                            .buyerTaxCode(invoiceData.getBuyerID())
                            .bankBranch(invoiceData.getBuyerBank())
                            .address(StringUtils.substringBeforeLast(invoiceData.getBuyerContact(), " "))
                            .tel(StringUtils.substringAfterLast(invoiceData.getBuyerContact(), " "))
                            .invoiceDate(DateUtils.parseDateTime(StringUtils.join(invoiceData.getInvoiceDate(), "-00:00:00"), DateUtils.FIX_DATETIME_FORMATTER))
                            .productVersion(invoiceData.getInvoiceType())
                            .receiptId(invoiceData.getInvoiceMachineNum())
                            .productName(invoiceData.getItems().isEmpty() ? null : invoiceData.getItems().get(0).getName())
                            .productSpec(invoiceData.getItems().isEmpty() ? null : invoiceData.getItems().get(0).getSpec())
                            .productUnit(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getUnit()))
                            .productCount(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getAmount()))
                            .productUnitPrice(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getPriceUnit()))
                            .totalPrice(new BigDecimal(invoiceData.getTaxAmount()))
                            .taxRate(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getTaxRate()))
                            .tax(invoiceData.getItems().isEmpty() ? null : new BigDecimal(invoiceData.getItems().get(0).getTaxSum()))
                            .taxTypeCode(invoiceData.getInvoiceType())
                            .build();

                } else {
                    taxScanResult.setIsValid("0");
                    taxScanResult.setIsVerified("0");
                    taxScanResult.setFailedReason(result.getDesc());
                }
                this.taxScanResultService.save(taxScanResult);
                this.baseMapper.insert(stdInvoiceRecord);
                this.stdInvoiceDtlRecordService.save(stdInvoiceDtlRecord);
            } catch (Exception e) {
                taxScanResult.setIsValid("0");
                taxScanResult.setIsVerified("0");
                taxScanResult.setFailedReason(e.getMessage());
            }
            httpSession.setAttribute(batchId, new BigDecimal((i + 1))
                    .divide(new BigDecimal(files.length), 2, BigDecimal.ROUND_HALF_EVEN)
                    .multiply(new BigDecimal(80))
                    .add(new BigDecimal(20)));
        }


    }
}
