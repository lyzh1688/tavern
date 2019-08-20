package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmOrderGszcDetail;
import java.util.List;

public interface CrmOrderGszcDetailMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(CrmOrderGszcDetail record);

    CrmOrderGszcDetail selectByPrimaryKey(String eventId);

    List<CrmOrderGszcDetail> selectAll();

    int updateByPrimaryKey(CrmOrderGszcDetail record);
}