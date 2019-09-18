package com.tuoyou.tavern.protocol.authcenter.spi;

import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuRoleRel;
import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.common.annotation.PathParam;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/9/18 <br>
 */
public interface AuthMenuService {
    AuthMenuResponse queryAuthMenuByRole(@PathParam(name = "roleId") String roleId);

    TavernResponse saveAuthRoleMenus(List<AuthMenuRoleRel> authMenuRoleRelList);
}
