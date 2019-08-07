package com.tuoyou.tavern.hrm.dao;

import com.tuoyou.tavern.hrm.entity.HrmUserBasicInfo;
import java.util.List;

public interface HrmUserBasicInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(HrmUserBasicInfo record);

    HrmUserBasicInfo selectByPrimaryKey(String userId);

    List<HrmUserBasicInfo> selectAll();

    int updateByPrimaryKey(HrmUserBasicInfo record);
}