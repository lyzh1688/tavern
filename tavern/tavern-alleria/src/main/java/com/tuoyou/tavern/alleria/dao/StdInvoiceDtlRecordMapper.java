package com.tuoyou.tavern.alleria.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.alleria.entity.StdInvoiceDtlRecord;
import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface StdInvoiceDtlRecordMapper  extends BaseMapper<StdInvoiceDtlRecord> {
    int deleteByPrimaryKey(@Param("fileId") String fileId, @Param("invoiceId") String invoiceId, @Param("invoiceCode") String invoiceCode);

    int insert(StdInvoiceDtlRecord record);

    StdInvoiceDtlRecord selectByPrimaryKey(@Param("fileId") String fileId, @Param("invoiceId") String invoiceId, @Param("invoiceCode") String invoiceCode);

    List<StdInvoiceDtlRecord> selectAll();

    int updateByPrimaryKey(StdInvoiceDtlRecord record);
}