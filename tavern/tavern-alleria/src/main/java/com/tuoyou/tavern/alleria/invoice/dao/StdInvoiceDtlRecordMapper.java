package com.tuoyou.tavern.alleria.invoice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecordVO;
import org.apache.ibatis.annotations.Param;

public interface StdInvoiceDtlRecordMapper extends BaseMapper<StdInvoiceDtlRecord> {

    IPage<StdInvoiceDtlRecord> selectStdInvoiceDtlRecord(Page page, @Param("query") StdInvoiceRecordDTO stdInvoiceRecordDTO);

}