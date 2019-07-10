package com.tuoyou.tavern.alleria.invoice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;

import java.util.List;
public interface StdInvoiceRecordMapper extends BaseMapper<StdInvoiceRecord> {
    int deleteByPrimaryKey(String fileId);

    int insert(StdInvoiceRecord record);

    StdInvoiceRecord selectByPrimaryKey(String fileId);

    List<StdInvoiceRecord> selectAll();

    int updateByPrimaryKey(StdInvoiceRecord record);
}