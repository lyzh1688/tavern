package com.tuoyou.tavern.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.auth.dao.AuthMenuMapper;
import com.tuoyou.tavern.auth.dao.AuthMenuRoleRelMapper;
import com.tuoyou.tavern.auth.service.AuthMenuRoleRelService;
import com.tuoyou.tavern.auth.service.AuthMenuService;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuRoleRel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Service
@AllArgsConstructor
public class AuthMenuRoleRelServiceImpl extends ServiceImpl<AuthMenuRoleRelMapper, AuthMenuRoleRel> implements AuthMenuRoleRelService {
}
