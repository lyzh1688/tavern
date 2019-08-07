package com.tuoyou.tavern.hrm.dao;

import com.tuoyou.tavern.hrm.entity.HrmRoleInfo;
import java.util.List;

public interface HrmRoleInfoMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(HrmRoleInfo record);

    HrmRoleInfo selectByPrimaryKey(String roleId);

    List<HrmRoleInfo> selectAll();

    int updateByPrimaryKey(HrmRoleInfo record);
}