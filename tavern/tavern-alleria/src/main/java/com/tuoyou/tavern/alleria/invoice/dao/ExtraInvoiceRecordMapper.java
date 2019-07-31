package com.tuoyou.tavern.alleria.invoice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.ExtraInvoiceDTO;
import com.tuoyou.tavern.protocol.alleria.model.ExtraInvoiceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtraInvoiceRecordMapper extends BaseMapper<ExtraInvoiceRecord> {

    IPage<ExtraInvoiceRecord> selectByPage(Page page, @Param("query") ExtraInvoiceDTO extraInvoiceDTO);

}