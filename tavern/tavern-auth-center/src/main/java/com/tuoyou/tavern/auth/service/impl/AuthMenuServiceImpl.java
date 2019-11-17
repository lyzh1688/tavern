package com.tuoyou.tavern.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.auth.dao.AuthMenuMapper;
import com.tuoyou.tavern.auth.service.AuthMenuService;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuVO;
import com.tuoyou.tavern.protocol.authcenter.model.AuthRoleInfo;
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
    public List<AuthMenuVO> getAuthMenuList(String roles, int menuType) {
        List<AuthMenuVO> authMenuList = new ArrayList<>();
        List<AuthMenuVO> menus = getAuthMenuByRoles(roles);
        for (AuthMenuVO menu : menus) {
            if (menu.getParentId() == null || menu.getParentId().equals(0L)) {
                menu.setLevel(0);
                if (!exists(authMenuList, menu)) {
                    authMenuList.add(menu);
                }
            }
        }
        authMenuList.sort(Comparator.comparing(AuthMenuVO::getOrderNum));
        setChildren(authMenuList, menus, menuType);
        return authMenuList;
    }

    private void setChildren(List<AuthMenuVO> menuList, List<AuthMenuVO> menus, int menuType) {
        for (AuthMenuVO authMenu : menuList) {
            List<AuthMenuVO> children = Lists.newArrayList();
            for (AuthMenuVO menu : menus) {
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
            children.sort(Comparator.comparing(AuthMenuVO::getOrderNum));
            setChildren(children, menus, menuType);
        }
    }

    @Override
    public List<AuthMenuVO> getAuthMenuByRoles(String roles) {
        if (StringUtils.isEmpty(roles)||roles.equalsIgnoreCase(HrmUserConstant.ADMIN)) {
            return this.baseMapper.list();
        }
        List<AuthMenuVO> authMenuVOList=  this.baseMapper.selectByRoleIds(Arrays.asList(StringUtils.split(roles, ",")));
        return authMenuVOList;
    }



    private boolean exists(List<AuthMenuVO> menuList, AuthMenuVO authMenu) {
        boolean exist = false;
        for (AuthMenuVO menu : menuList) {
            if (menu.getMenuId().equals(authMenu.getMenuId())) {
                exist = true;
            }
        }
        return exist;
    }

}
