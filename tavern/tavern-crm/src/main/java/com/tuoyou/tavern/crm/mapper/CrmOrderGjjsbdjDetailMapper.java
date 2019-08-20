package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmOrderGjjsbdjDetail;
import java.util.List;

public interface CrmOrderGjjsbdjDetailMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(CrmOrderGjjsbdjDetail record);

    CrmOrderGjjsbdjDetail selectByPrimaryKey(String eventId);

    List<CrmOrderGjjsbdjDetail> selectAll();

    int updateByPrimaryKey(CrmOrderGjjsbdjDetail record);
}