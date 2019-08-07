package com.tuoyou.tavern.crm.dao;

import com.tuoyou.tavern.crm.entity.CrmCustomBasicInfo;
import java.util.List;

public interface CrmCustomBasicInfoMapper {
    int deleteByPrimaryKey(String customId);

    int insert(CrmCustomBasicInfo record);

    CrmCustomBasicInfo selectByPrimaryKey(String customId);

    List<CrmCustomBasicInfo> selectAll();

    int updateByPrimaryKey(CrmCustomBasicInfo record);
}