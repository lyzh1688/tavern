package com.tuoyou.tavern.alleria.invoice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface StdInvoiceRecordMapper extends BaseMapper<StdInvoiceRecord> {

    void updateFileStatus(@Param("fileId")String fileId,@Param("valid")String valid);
}