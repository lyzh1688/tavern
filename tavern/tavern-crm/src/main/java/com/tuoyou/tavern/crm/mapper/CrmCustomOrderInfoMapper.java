package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmCustomOrderInfo;
import java.util.List;

public interface CrmCustomOrderInfoMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(CrmCustomOrderInfo record);

    CrmCustomOrderInfo selectByPrimaryKey(String orderId);

    List<CrmCustomOrderInfo> selectAll();

    int updateByPrimaryKey(CrmCustomOrderInfo record);
}