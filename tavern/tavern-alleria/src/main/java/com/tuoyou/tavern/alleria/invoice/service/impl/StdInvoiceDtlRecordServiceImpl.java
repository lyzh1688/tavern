package com.tuoyou.tavern.alleria.invoice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.invoice.dao.StdInvoiceDtlRecordMapper;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.common.core.util.CommonUtils;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
public class StdInvoiceDtlRecordServiceImpl extends ServiceImpl<StdInvoiceDtlRecordMapper, StdInvoiceDtlRecord> implements StdInvoiceDtlRecordService {

    @Override
    public IPage<StdInvoiceDtlRecordVO> getStdInvoiceDtlRecord(Page page, StdInvoiceRecordDTO stdInvoiceRecordDTO) {
        IPage<StdInvoiceDtlRecord> stdInvoiceDtlRecordIPage = this.baseMapper.selectStdInvoiceDtlRecord(page, stdInvoiceRecordDTO);
        List<StdInvoiceDtlRecordVO> stdInvoiceDtlRecordVOList = stdInvoiceDtlRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    StdInvoiceDtlRecordVO stdInvoiceDtlRecordVO = new StdInvoiceDtlRecordVO();
                    BeanUtils.copyProperties(record, stdInvoiceDtlRecordVO);
                    if(record.getInvoiceDate() != null)
                    stdInvoiceDtlRecordVO.setInvoiceDate(DateUtils.formatDate(record.getInvoiceDate().toLocalDate(), DateUtils.DEFAULT_DATE_FORMATTER));
                    return stdInvoiceDtlRecordVO;
                }).collect(Collectors.toList());
        return CommonUtils.newIPage(stdInvoiceDtlRecordIPage, stdInvoiceDtlRecordVOList);
    }

}
