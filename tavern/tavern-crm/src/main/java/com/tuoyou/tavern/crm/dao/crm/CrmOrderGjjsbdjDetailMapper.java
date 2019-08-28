package com.tuoyou.tavern.crm.dao.crm;

import com.tuoyou.tavern.protocol.crm.model.CrmOrderGjjsbdjDetail;
import java.util.List;

public interface CrmOrderGjjsbdjDetailMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(CrmOrderGjjsbdjDetail record);

    CrmOrderGjjsbdjDetail selectByPrimaryKey(String eventId);

    List<CrmOrderGjjsbdjDetail> selectAll();

    int updateByPrimaryKey(CrmOrderGjjsbdjDetail record);
}