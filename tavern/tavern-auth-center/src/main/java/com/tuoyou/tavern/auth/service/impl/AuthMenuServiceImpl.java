package com.tuoyou.tavern.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.auth.dao.AuthMenuMapper;
import com.tuoyou.tavern.auth.service.AuthMenuService;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.hrm.constants.HrmUserConstant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Service
@AllArgsConstructor
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenu> implements AuthMenuService {


    @Override
    public List<AuthMenu> getAuthMenuList(String roles, int menuType) {
        List<AuthMenu> authMenuList = new ArrayList<>();
        List<AuthMenu> menus = getAuthMenuByRoles(roles);
        for (AuthMenu menu : menus) {
            if (menu.getParentId() == null || menu.getParentId().equals("0")) {
                menu.setLevel(0);
                if (!exists(authMenuList, menu)) {
                    authMenuList.add(menu);
                }
            }
        }
        authMenuList.sort(Comparator.comparing(AuthMenu::getOrderNum));
        setChildren(authMenuList, menus, menuType);
        return authMenuList;
    }

    private void setChildren(List<AuthMenu> menuList, List<AuthMenu> menus, int menuType) {
        for (AuthMenu authMenu : menuList) {
            List<AuthMenu> children = Lists.newArrayList();
            for (AuthMenu menu : menus) {
                if (menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue;
                }
                if (authMenu.getMenuId() != null && authMenu.getMenuId().equals(menu.getParentId())) {
                    menu.setParentName(authMenu.getMenuName());
                    menu.setLevel(authMenu.getLevel() + 1);
                    if (!exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            authMenu.setChildren(children);
            children.sort(Comparator.comparing(AuthMenu::getOrderNum));
            setChildren(children, menus, menuType);
        }
    }

    @Override
    public List<AuthMenu> getAuthMenuByRoles(String roles) {
        if (StringUtils.isEmpty(roles)) {
            return this.list();
        }
        return this.baseMapper.selectByRoleIds(Arrays.asList(StringUtils.split(roles, ",")));
    }

    private boolean exists(List<AuthMenu> menuList, AuthMenu authMenu) {
        boolean exist = false;
        for (AuthMenu menu : menuList) {
            if (menu.getMenuId().equals(authMenu.getMenuId())) {
                exist = true;
            }
        }
        return exist;
    }

}
