package com.tuoyou.tavern.auth.dao;

import com.tuoyou.tavern.auth.entity.AuthMenuRoleRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthMenuRoleRelMapper {
    int deleteByPrimaryKey(@Param("menuId") String menuId, @Param("roleId") String roleId);

    int insert(AuthMenuRoleRel record);

    AuthMenuRoleRel selectByPrimaryKey(@Param("menuId") String menuId, @Param("roleId") String roleId);

    List<AuthMenuRoleRel> selectAll();

    int updateByPrimaryKey(AuthMenuRoleRel record);
}