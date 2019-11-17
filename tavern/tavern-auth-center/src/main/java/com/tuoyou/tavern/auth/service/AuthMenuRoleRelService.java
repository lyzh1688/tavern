package com.tuoyou.tavern.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuRoleRel;
import com.tuoyou.tavern.protocol.authcenter.model.AuthRoleInfo;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/10 <br>
 */
public interface AuthMenuRoleRelService extends IService<AuthMenuRoleRel> {
    AuthRoleInfo getAuthRoleInfo(String roles, String pageUrl);
}
