package com.tuoyou.tavern.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuRoleRel;
import com.tuoyou.tavern.protocol.authcenter.model.AuthRoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMenuRoleRelMapper extends BaseMapper<AuthMenuRoleRel> {
    AuthRoleInfo selectPageRole(@Param("list") List<String> roleIds,
                                @Param("pageUrl") String pageUrl);
}