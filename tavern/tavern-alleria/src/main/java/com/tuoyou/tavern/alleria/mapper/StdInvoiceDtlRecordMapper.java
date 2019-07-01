package com.tuoyou.tavern.alleria.mapper;

import com.tuoyou.tavern.alleria.domain.StdInvoiceDtlRecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StdInvoiceDtlRecordMapper {
    int deleteByPrimaryKey(@Param("fileId") String fileId, @Param("invoiceId") String invoiceId, @Param("invoiceCode") String invoiceCode);

    int insert(StdInvoiceDtlRecord record);

    StdInvoiceDtlRecord selectByPrimaryKey(@Param("fileId") String fileId, @Param("invoiceId") String invoiceId, @Param("invoiceCode") String invoiceCode);

    List<StdInvoiceDtlRecord> selectAll();

    int updateByPrimaryKey(StdInvoiceDtlRecord record);
}