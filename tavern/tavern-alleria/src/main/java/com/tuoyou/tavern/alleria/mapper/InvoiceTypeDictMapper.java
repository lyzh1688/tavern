package com.tuoyou.tavern.alleria.mapper;

import com.tuoyou.tavern.alleria.domain.InvoiceTypeDict;
import java.util.List;

public interface InvoiceTypeDictMapper {
    int deleteByPrimaryKey(String invoiceType);

    int insert(InvoiceTypeDict record);

    InvoiceTypeDict selectByPrimaryKey(String invoiceType);

    List<InvoiceTypeDict> selectAll();

    int updateByPrimaryKey(InvoiceTypeDict record);
}