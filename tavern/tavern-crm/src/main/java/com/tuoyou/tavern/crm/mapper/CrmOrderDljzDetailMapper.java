package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmOrderDljzDetail;
import java.util.List;

public interface CrmOrderDljzDetailMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(CrmOrderDljzDetail record);

    CrmOrderDljzDetail selectByPrimaryKey(String eventId);

    List<CrmOrderDljzDetail> selectAll();

    int updateByPrimaryKey(CrmOrderDljzDetail record);
}