package com.tuoyou.tavern.alleria.invoice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.alleria.model.InvoiceTypeDict;

import java.util.List;
public interface InvoiceTypeDictMapper extends BaseMapper<InvoiceTypeDict> {
    int deleteByPrimaryKey(String invoiceType);

    int insert(InvoiceTypeDict record);

    InvoiceTypeDict selectByPrimaryKey(String invoiceType);

    List<InvoiceTypeDict> selectAll();

    int updateByPrimaryKey(InvoiceTypeDict record);
}