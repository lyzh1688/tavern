package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmBusinessDict;
import java.util.List;

public interface CrmBusinessDictMapper {
    int deleteByPrimaryKey(String businessId);

    int insert(CrmBusinessDict record);

    CrmBusinessDict selectByPrimaryKey(String businessId);

    List<CrmBusinessDict> selectAll();

    int updateByPrimaryKey(CrmBusinessDict record);
}