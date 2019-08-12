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
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/8/12 <br>
 */
@Service
@AllArgsConstructor
public class AuthMenuRoleRelServiceImpl extends ServiceImpl<AuthMenuRoleRelMapper, AuthMenuRoleRel> implements AuthMenuRoleRelService {
}
