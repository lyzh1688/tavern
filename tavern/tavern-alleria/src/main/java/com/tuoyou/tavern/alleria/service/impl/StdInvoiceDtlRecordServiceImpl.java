package com.tuoyou.tavern.alleria.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.dao.StdInvoiceDtlRecordMapper;
import com.tuoyou.tavern.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.alleria.entity.StdInvoiceDtlRecord;
import com.tuoyou.tavern.alleria.entity.StdInvoiceRecord;
import com.tuoyou.tavern.alleria.entity.TaxScanResult;
import com.tuoyou.tavern.alleria.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.service.StdInvoiceRecordService;
import com.tuoyou.tavern.alleria.service.TaxScanResultService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.time.LocalDateTime;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
@AllArgsConstructor
public class StdInvoiceDtlRecordServiceImpl extends ServiceImpl<StdInvoiceDtlRecordMapper, StdInvoiceDtlRecord> implements StdInvoiceDtlRecordService {


    private final TaxScanResultService taxScanResultService;
    private final StdInvoiceRecordService stdInvoiceRecordService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateZzsInvoiceDtl(ZZSInvoiceKeyField zzsInvoiceKeyField) {

        //更改invoice
        //更改dtl
        //更改scan
        StdInvoiceRecord stdInvoiceRecord = new StdInvoiceRecord();
        stdInvoiceRecord.setFileId(zzsInvoiceKeyField.getFileId());
        stdInvoiceRecord.setIsValid("1");
        stdInvoiceRecord.setUpdateDate(LocalDateTime.now());
        this.stdInvoiceRecordService.update(stdInvoiceRecord, Wrappers.<StdInvoiceRecord>update().lambda()
                .eq(StdInvoiceRecord::getFileId, zzsInvoiceKeyField.getFileId()));

        TaxScanResult taxScanResult = new TaxScanResult();
        BeanUtils.copyProperties(zzsInvoiceKeyField, taxScanResult);
        taxScanResult.setUpdateDate(LocalDateTime.now());

        this.taxScanResultService.update(taxScanResult, Wrappers.<TaxScanResult>update().lambda()
                .eq(TaxScanResult::getFileId, zzsInvoiceKeyField.getFileId()));

        StdInvoiceDtlRecord stdInvoiceDtlRecord = new StdInvoiceDtlRecord();
        BeanUtils.copyProperties(zzsInvoiceKeyField, stdInvoiceDtlRecord);
        this.update(stdInvoiceDtlRecord, Wrappers.<StdInvoiceDtlRecord>update().lambda()
                .eq(StdInvoiceDtlRecord::getFileId, zzsInvoiceKeyField.getFileId()));


        return Boolean.TRUE;
    }

}
