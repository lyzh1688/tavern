package com.tuoyou.tavern.alleria.invoice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StdInvoiceRecordMapper extends BaseMapper<StdInvoiceRecord> {

    void updateFileStatus(@Param("batchId") String batchId, @Param("valid") String valid);

    IPage<StdInvoiceRecord> selectStdInvoiceRecord(Page page, @Param("query") StdInvoiceRecordDTO stdInvoiceRecordDTO);
}