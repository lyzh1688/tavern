package com.tuoyou.tavern.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMenuMapper extends BaseMapper<AuthMenu> {

    List<AuthMenuVO> selectByRoleIds(@Param("list") List<String> roleIds);

    List<AuthMenuVO> list();

}