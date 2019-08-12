package com.tuoyou.tavern.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMenuMapper extends BaseMapper<AuthMenu> {

    List<AuthMenu> selectByRoleIds(@Param("list") List<String> roleIds);

}