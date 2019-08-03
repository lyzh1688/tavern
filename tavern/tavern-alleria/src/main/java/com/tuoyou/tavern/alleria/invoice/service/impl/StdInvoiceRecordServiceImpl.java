package com.tuoyou.tavern.alleria.invoice.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.alleria.configuration.TTLContext;
import com.tuoyou.tavern.alleria.invoice.dao.StdInvoiceRecordMapper;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import com.tuoyou.tavern.alleria.util.CommonUtils;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.alleria.zzs.service.ZzsTaxScanResultService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.invoice.common.libs.excel.ExcelListener;
import com.tuoyou.tavern.invoice.ocr.libs.client.TBOcrAgent;
import com.tuoyou.tavern.invoice.verify.libs.client.ZBJVerifyAgent;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.file.InvoiceExcel;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecordVO;
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
    private ZzsTaxScanResultService taxScanResultService;
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
            JSONObject fileUploadObject = CommonUtils.percentageRecord(i, files.length);
            ttlContext.putValue(batchId, fileUploadObject);
            log.info("stdInvoice upload batchId: {} file complete: {}", batchId, fileUploadObject.getDouble("percentage"));
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
        return CommonUtils.newIPage(stdInvoiceRecordIPage, stdInvoiceRecordVOList);
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
