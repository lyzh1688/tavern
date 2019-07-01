package com.tuoyou.tavern.alleria.mapper;

import com.tuoyou.tavern.alleria.domain.StdInvoiceRecord;
import java.util.List;

public interface StdInvoiceRecordMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(StdInvoiceRecord record);

    StdInvoiceRecord selectByPrimaryKey(String fileId);

    List<StdInvoiceRecord> selectAll();

    int updateByPrimaryKey(StdInvoiceRecord record);
}