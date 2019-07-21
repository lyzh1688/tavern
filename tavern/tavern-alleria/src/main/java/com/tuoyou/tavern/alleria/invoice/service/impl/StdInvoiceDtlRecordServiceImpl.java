package com.tuoyou.tavern.alleria.invoice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.invoice.dao.StdInvoiceDtlRecordMapper;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecordVO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResult;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import com.tuoyou.tavern.alleria.invoice.service.TaxScanResultService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
public class StdInvoiceDtlRecordServiceImpl extends ServiceImpl<StdInvoiceDtlRecordMapper, StdInvoiceDtlRecord> implements StdInvoiceDtlRecordService {


    @Autowired
    private  TaxScanResultService taxScanResultService;
    @Autowired
    private  StdInvoiceRecordService stdInvoiceRecordService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateZzsInvoiceDtl(ZZSInvoiceKeyField zzsInvoiceKeyField) {

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


    }

    @Override
    public IPage<StdInvoiceDtlRecordVO> getStdInvoiceDtlRecord(Page page, StdInvoiceRecordDTO stdInvoiceRecordDTO) {
        IPage<StdInvoiceDtlRecord> stdInvoiceDtlRecordIPage = this.baseMapper.selectStdInvoiceDtlRecord(page, stdInvoiceRecordDTO);
        List<StdInvoiceDtlRecordVO> stdInvoiceDtlRecordVOList = stdInvoiceDtlRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    StdInvoiceDtlRecordVO stdInvoiceDtlRecordVO = new StdInvoiceDtlRecordVO();
                    BeanUtils.copyProperties(record, stdInvoiceDtlRecordVO);
                    if(record.getInvoiceDate() != null)
                    stdInvoiceDtlRecordVO.setInvoiceDate(DateUtils.formatDateTime(record.getInvoiceDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    return stdInvoiceDtlRecordVO;
                }).collect(Collectors.toList());
        Page<StdInvoiceDtlRecordVO> stdInvoiceRecordVOPage = new Page<>();
        stdInvoiceRecordVOPage.setRecords(stdInvoiceDtlRecordVOList);
        stdInvoiceRecordVOPage.setCurrent(stdInvoiceDtlRecordIPage.getCurrent());
        stdInvoiceRecordVOPage.setSize(stdInvoiceDtlRecordIPage.getSize());
        stdInvoiceRecordVOPage.setCurrent(stdInvoiceDtlRecordIPage.getCurrent());
        stdInvoiceRecordVOPage.setTotal(stdInvoiceDtlRecordIPage.getTotal());
        return stdInvoiceRecordVOPage;
    }

}
