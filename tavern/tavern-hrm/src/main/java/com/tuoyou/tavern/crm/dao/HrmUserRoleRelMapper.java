package com.tuoyou.tavern.hrm.dao;

import com.tuoyou.tavern.hrm.entity.HrmUserRoleRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrmUserRoleRelMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);

    int insert(HrmUserRoleRel record);

    HrmUserRoleRel selectByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);

    List<HrmUserRoleRel> selectAll();

    int updateByPrimaryKey(HrmUserRoleRel record);
}