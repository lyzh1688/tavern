package com.tuoyou.tavern.alleria.invoice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.invoice.dao.TaxScanResultMapper;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResult;
import com.tuoyou.tavern.alleria.invoice.service.TaxScanResultService;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResultVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
@AllArgsConstructor
public class TaxScanResultServiceImpl extends ServiceImpl<TaxScanResultMapper, TaxScanResult> implements TaxScanResultService {

    private final StdInvoiceDtlRecordService stdInvoiceDtlRecordService;

    @Override
    public IPage<TaxScanResultVO> getTaxScanResult(Page page, TaxScanResultDTO taxScanResultDTO) {
        IPage<TaxScanResult> taxScanResultIPage = this.baseMapper.selectTaxScanResult(page, taxScanResultDTO);
        List<TaxScanResultVO> fileUploadRecordVOList = taxScanResultIPage.getRecords()
                .stream()
                .map(record -> {
                    TaxScanResultVO taxScanResultVO = new TaxScanResultVO();
                    BeanUtils.copyProperties(record, taxScanResultVO);
                    taxScanResultVO.setUpdateDate(DateUtils.formatDateTime(record.getUpdateDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    return taxScanResultVO;
                }).collect(Collectors.toList());
        Page<TaxScanResultVO> taxScanResultVOPage = new Page<>();
        taxScanResultVOPage.setRecords(fileUploadRecordVOList);
        taxScanResultVOPage.setCurrent(taxScanResultIPage.getCurrent());
        taxScanResultVOPage.setSize(taxScanResultIPage.getSize());
        taxScanResultVOPage.setCurrent(taxScanResultIPage.getCurrent());
        taxScanResultVOPage.setTotal(taxScanResultIPage.getTotal());
        return taxScanResultVOPage;
    }

    @Override
    public TaxScanResultVO getTaxScanResultByFileId(String fileId) {
        TaxScanResult taxScanResult = this.getById(fileId);
        TaxScanResultVO taxScanResultVO = new TaxScanResultVO();
        BeanUtils.copyProperties(taxScanResult, taxScanResultVO);
        StdInvoiceDtlRecord stdInvoiceDtlRecord = this.stdInvoiceDtlRecordService.getOne(Wrappers.<StdInvoiceDtlRecord>query().lambda()
                .eq(StdInvoiceDtlRecord::getFileId, fileId));
        if (stdInvoiceDtlRecord != null) {
            taxScanResultVO.setImageUrl(stdInvoiceDtlRecord.getImageUrl());
        }
        return taxScanResultVO;
    }
}
