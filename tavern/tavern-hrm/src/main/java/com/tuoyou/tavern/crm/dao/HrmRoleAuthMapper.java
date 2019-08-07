package com.tuoyou.tavern.hrm.dao;

import com.tuoyou.tavern.hrm.entity.HrmRoleAuth;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrmRoleAuthMapper {
    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("item") String item);

    int insert(HrmRoleAuth record);

    HrmRoleAuth selectByPrimaryKey(@Param("roleId") String roleId, @Param("item") String item);

    List<HrmRoleAuth> selectAll();

    int updateByPrimaryKey(HrmRoleAuth record);
}