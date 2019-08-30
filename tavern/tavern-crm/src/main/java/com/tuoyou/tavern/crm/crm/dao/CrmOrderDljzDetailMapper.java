package com.tuoyou.tavern.crm.crm.dao;

import com.tuoyou.tavern.protocol.crm.model.CrmOrderDljzDetail;
import java.util.List;

public interface CrmOrderDljzDetailMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(CrmOrderDljzDetail record);

    CrmOrderDljzDetail selectByPrimaryKey(String eventId);

    List<CrmOrderDljzDetail> selectAll();

    int updateByPrimaryKey(CrmOrderDljzDetail record);
}