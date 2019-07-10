package com.tuoyou.tavern.alleria.invoice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.alleria.model.ExtraInvoiceRecord;

import java.util.List;
public interface ExtraInvoiceRecordMapper extends BaseMapper<ExtraInvoiceRecord> {
    int deleteByPrimaryKey(String recordId);

    int insert(ExtraInvoiceRecord record);

    ExtraInvoiceRecord selectByPrimaryKey(String recordId);

    List<ExtraInvoiceRecord> selectAll();

    int updateByPrimaryKey(ExtraInvoiceRecord record);
}