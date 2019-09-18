package com.tuoyou.tavern.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuVO;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/10 <br>
 */
public interface AuthMenuService extends IService<AuthMenu> {

    List<AuthMenuVO> getAuthMenuList(String roles, int menuType);

    List<AuthMenuVO> getAuthMenuByRoles(String roles);

}
