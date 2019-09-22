package com.tuoyou.tavern.protocol.authcenter.spi;

import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuRoleRel;
import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuPermissionResponse;
import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.common.annotation.PathParam;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
public interface AuthMenuService {
    AuthMenuResponse queryAuthMenuByRole(@PathParam(name = "roleId") String roleId);

    AuthMenuPermissionResponse queryAuthMenuPermissionByRole(@PathParam(name = "roleId") String roleId);

    TavernResponse saveAuthRoleMenus(List<AuthMenuRoleRel> authMenuRoleRelList);
}
