package com.tuoyou.tavern.alleria.mapper;

import com.tuoyou.tavern.alleria.domain.ExtraInvoiceRecord;
import java.util.List;

public interface ExtraInvoiceRecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(ExtraInvoiceRecord record);

    ExtraInvoiceRecord selectByPrimaryKey(String recordId);

    List<ExtraInvoiceRecord> selectAll();

    int updateByPrimaryKey(ExtraInvoiceRecord record);
}