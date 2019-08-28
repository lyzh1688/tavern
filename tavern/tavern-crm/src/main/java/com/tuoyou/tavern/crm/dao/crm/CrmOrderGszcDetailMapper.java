package com.tuoyou.tavern.crm.dao.crm;

import com.tuoyou.tavern.protocol.crm.model.CrmOrderGszcDetail;
import java.util.List;

public interface CrmOrderGszcDetailMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(CrmOrderGszcDetail record);

    CrmOrderGszcDetail selectByPrimaryKey(String eventId);

    List<CrmOrderGszcDetail> selectAll();

    int updateByPrimaryKey(CrmOrderGszcDetail record);
}