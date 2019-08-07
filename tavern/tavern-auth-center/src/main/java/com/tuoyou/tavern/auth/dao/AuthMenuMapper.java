package com.tuoyou.tavern.auth.dao;

import com.tuoyou.tavern.auth.entity.AuthMenu;
import java.util.List;

public interface AuthMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(AuthMenu record);

    AuthMenu selectByPrimaryKey(String menuId);

    List<AuthMenu> selectAll();

    int updateByPrimaryKey(AuthMenu record);
}