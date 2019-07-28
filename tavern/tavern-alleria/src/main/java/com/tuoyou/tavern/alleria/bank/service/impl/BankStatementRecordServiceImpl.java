package com.tuoyou.tavern.alleria.bank.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.alleria.bank.dao.BankStatementRecordMapper;
import com.tuoyou.tavern.alleria.bank.service.BankStatementDtlCcblRecordService;
import com.tuoyou.tavern.alleria.bank.service.BankStatementRecordService;
import com.tuoyou.tavern.alleria.configuration.TTLContext;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.invoice.common.libs.excel.ExcelListener;
import com.tuoyou.tavern.protocol.alleria.common.FileUploadStatus;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDTO;
import com.tuoyou.tavern.protocol.alleria.file.BankStatementExcel;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementDtlCcblRecord;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecordVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
@Slf4j
@AllArgsConstructor
@Service
public class BankStatementRecordServiceImpl extends ServiceImpl<BankStatementRecordMapper, BankStatementRecord> implements BankStatementRecordService {

    private final BankStatementDtlCcblRecordService bankStatementDtlCcblRecordService;
    private final TTLContext ttlContext;

    @Override
    public IPage<BankStatementRecordVO> getBankStatementRecord(Page page, BankStatementDTO bankStatementDTO) {
        IPage<BankStatementRecord> bankStatementRecordIPage = this.baseMapper.selectBankStatementRecord(page, bankStatementDTO);
        List<BankStatementRecordVO> bankStatementRecordVOList = bankStatementRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    BankStatementRecordVO bankStatementRecordVO = new BankStatementRecordVO();
                    BeanUtils.copyProperties(record, bankStatementRecordVO);
                    bankStatementRecordVO.setAccountPeriod(DateUtils.formatDateTime(record.getAccountPeriod(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    bankStatementRecordVO.setUpdateDate(DateUtils.formatDateTime(record.getUpdateDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    return bankStatementRecordVO;
                }).collect(Collectors.toList());
        Page<BankStatementRecordVO> bankStatementRecordVOPage = new Page<>();
        bankStatementRecordVOPage.setRecords(bankStatementRecordVOList);
        bankStatementRecordVOPage.setCurrent(bankStatementRecordIPage.getCurrent());
        bankStatementRecordVOPage.setSize(bankStatementRecordIPage.getSize());
        bankStatementRecordVOPage.setCurrent(bankStatementRecordIPage.getCurrent());
        bankStatementRecordVOPage.setTotal(bankStatementRecordIPage.getTotal());
        return bankStatementRecordVOPage;

    }

    @Override
    public void parseBankStatement(FileTransfer fileTransfer) {
        String destLocation = fileTransfer.getDestLocation();
        String batchId = fileTransfer.getBatchId();
        File[] files = new File(destLocation).listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            List<BankStatementExcel> bankStatementExcelList;
            ExcelListener<BankStatementExcel> listener = new ExcelListener<BankStatementExcel>();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String pattern = StringUtils.substringAfterLast(file.getName(), ".");
            if (pattern.equals("xls")) {
                (new ExcelReader(inputStream, ExcelTypeEnum.XLS, (Object) null, listener)).read(new Sheet(1, 1, BankStatementExcel.class));
            } else {
                (new ExcelReader(inputStream, ExcelTypeEnum.XLSX, (Object) null, listener)).read(new Sheet(1, 1, BankStatementExcel.class));
            }
            bankStatementExcelList = listener.getDatas();
            //10条一组
            List<List<BankStatementExcel>> partList = Lists.partition(bankStatementExcelList, 10);
            for (int j = 0; j < partList.size(); j++) {
                List<BankStatementExcel> list = partList.get(j);
                List<BankStatementRecord> bankStatementRecordList =
                        list.stream()
                                .map(dtl -> {
                                    BankStatementRecord bankStatementRecord = new BankStatementRecord();
                                    bankStatementRecord.setFileId(StringUtils.substringAfterLast(dtl.getDetailId(), "-"));
                                    bankStatementRecord.setBatchId(batchId);
                                    bankStatementRecord.setFileName(file.getName());
                                    bankStatementRecord.setIsValid("1");
                                    bankStatementRecord.setUpdateDate(LocalDateTime.now());
                                    bankStatementRecord.setCustomId(dtl.getCustomAccntId());
                                    bankStatementRecord.setBankId(dtl.getCustomAccntId());
                                    return bankStatementRecord;
                                }).collect(Collectors.toList());
                List<BankStatementDtlCcblRecord> bankStatementDtlCcblRecordList = list.stream()
                        .map(dtl -> {
                            BankStatementDtlCcblRecord bankStatementDtlCcblRecord = new BankStatementDtlCcblRecord();
                            BeanUtils.copyProperties(dtl, bankStatementDtlCcblRecord);
                            String debitAmount = StringUtils.replace(dtl.getDebitAmount(), ",", "");
                            String creditAmount = StringUtils.replace(dtl.getCreditAmount(), ",", "");
                            String balance = StringUtils.replace(dtl.getBalance(), ",", "");
                            bankStatementDtlCcblRecord.setFileId(StringUtils.substringAfterLast(dtl.getDetailId(), "-"));
                            bankStatementDtlCcblRecord.setTradeDate(StringUtils.isEmpty(dtl.getTradeDate()) ? null : DateUtils.parseDateTime(dtl.getTradeDate(), DateUtils.SIMPLE_DATETIME_FORMATTER));
                            bankStatementDtlCcblRecord.setDebitAmount(StringUtils.isEmpty(debitAmount) ? null : new BigDecimal(debitAmount));
                            bankStatementDtlCcblRecord.setCreditAmount(StringUtils.isEmpty(creditAmount) ? null : new BigDecimal(creditAmount));
                            bankStatementDtlCcblRecord.setAccountingDate(StringUtils.isEmpty(dtl.getAccountingDate()) ? null : DateUtils.parseDateTime(StringUtils.join(dtl.getAccountingDate(), " 00:00:00"), DateUtils.SIMPLE_DATETIME_FORMATTER));
                            bankStatementDtlCcblRecord.setBalance(StringUtils.isEmpty(balance) ? null : new BigDecimal(balance));
                            return bankStatementDtlCcblRecord;
                        }).collect(Collectors.toList());
                this.saveOrUpdateBatch(bankStatementRecordList);
                this.bankStatementDtlCcblRecordService.saveOrUpdateBatch(bankStatementDtlCcblRecordList);
            }
            JSONObject fileUploadObject = new JSONObject();
            fileUploadObject.put("status", FileUploadStatus.IN_PROCESS);
            double percentage = new BigDecimal((i + 1))
                    .divide(new BigDecimal(files.length), 2, BigDecimal.ROUND_HALF_EVEN)
                    .multiply(new BigDecimal(80))
                    .add(new BigDecimal(20)).doubleValue();
            fileUploadObject.put("percentage", percentage);
            ttlContext.putValue(batchId, fileUploadObject);
            log.info("batchId: {} bank statement file complete: {}", batchId, percentage);
        }

    }

    @Override
    public void updateStatus(String batchId, String valid) {
        this.updateStatus(batchId, valid);
    }

}
