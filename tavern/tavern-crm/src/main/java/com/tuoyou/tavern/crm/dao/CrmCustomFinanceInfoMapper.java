package com.tuoyou.tavern.crm.dao;

import com.tuoyou.tavern.crm.entity.CrmCustomFinanceInfo;
import java.util.List;

public interface CrmCustomFinanceInfoMapper {
    int deleteByPrimaryKey(String customId);

    int insert(CrmCustomFinanceInfo record);

    CrmCustomFinanceInfo selectByPrimaryKey(String customId);

    List<CrmCustomFinanceInfo> selectAll();

    int updateByPrimaryKey(CrmCustomFinanceInfo record);
}