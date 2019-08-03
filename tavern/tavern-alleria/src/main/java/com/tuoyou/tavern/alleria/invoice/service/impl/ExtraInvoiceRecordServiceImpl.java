package com.tuoyou.tavern.alleria.invoice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.invoice.dao.ExtraInvoiceRecordMapper;
import com.tuoyou.tavern.alleria.invoice.service.ExtraInvoiceRecordService;
import com.tuoyou.tavern.alleria.util.CommonUtils;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.ExtraInvoiceDTO;
import com.tuoyou.tavern.protocol.alleria.model.ExtraInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.model.ExtraInvoiceRecordVO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/7/12 <br>
 */
@Service
public class ExtraInvoiceRecordServiceImpl extends ServiceImpl<ExtraInvoiceRecordMapper, ExtraInvoiceRecord> implements ExtraInvoiceRecordService {


    @Override
    public IPage<ExtraInvoiceRecordVO> getByPage(Page page, ExtraInvoiceDTO extraInvoiceDTO) {
        IPage<ExtraInvoiceRecord> extraInvoiceRecordIPage = this.baseMapper.selectByPage(page, extraInvoiceDTO);
        List<ExtraInvoiceRecordVO> extraInvoiceRecordVOList = extraInvoiceRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    ExtraInvoiceRecordVO extraInvoiceRecordVO = new ExtraInvoiceRecordVO();
                    BeanUtils.copyProperties(record, extraInvoiceRecordVO);
                    if (record.getAccountPeriod() != null) {
                        extraInvoiceRecordVO.setAccountPeriod(DateUtils.formatDate(record.getAccountPeriod(), DateUtils.DEFAULT_DATE_FORMATTER));
                    }
                    extraInvoiceRecordVO.setTotalAmount(record.getTotalAmount() != null ? record.getTotalAmount().toPlainString() : null);
                    extraInvoiceRecordVO.setItemCount(record.getItemCount() != null ? record.getItemCount().toString() : null);
                    return extraInvoiceRecordVO;
                }).collect(Collectors.toList());
        return CommonUtils.newIPage(extraInvoiceRecordIPage, extraInvoiceRecordVOList);
    }
}
