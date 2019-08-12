package com.tuoyou.tavern.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.auth.dao.AuthMenuMapper;
import com.tuoyou.tavern.auth.service.AuthMenuService;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenu> implements AuthMenuService {


    @Override
    public List<AuthMenu> getAuthMenuList(String userAccnt, String menuType) {


        return null;
    }

    @Override
    public List<AuthMenu> getAuthMenuByAccnt(String userAccnt) {
        if (Objects.isNull(userAccnt)) {
            this.list();
        }
        List<AuthMenu> authMenuList = this.
        return null;
    }


}
