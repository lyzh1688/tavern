package com.tuoyou.tavern.protocol.authcenter.spi;

import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuResponse;
import com.tuoyou.tavern.protocol.common.RequestMethods;
import com.tuoyou.tavern.protocol.common.SpiDescription;
import com.tuoyou.tavern.protocol.common.annotation.PathParam;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/9/18 <br>
 */
public class AuthMenuServiceProviderDesc {
    public static Map<Class<?>, Map<String, SpiDescription>> serviceProviderDesc = new HashMap<>();

    static {
        Map<String, SpiDescription> authMenuServiceProviderDesc = new HashMap<>();
        SpiDescription queryAuthMenuByRole = new SpiDescription.Builder()
                .serviceClass(AuthMenuService.class)
                .requestMethod(RequestMethods.GET)
                .resourcePath("/menu/findRoleMenus?roleId=${roleId}")
                .serviceApi("queryAuthMenuByRole")
                .build();
        SpiDescription saveAuthRoleMenus = new SpiDescription.Builder()
                .serviceClass(AuthMenuService.class)
                .requestMethod(RequestMethods.POST)
                .resourcePath("/menu/saveAuthRoleMenus")
                .serviceApi("saveAuthRoleMenus")
                .build();

        authMenuServiceProviderDesc.put("queryAuthMenuByRole", queryAuthMenuByRole);
        authMenuServiceProviderDesc.put("saveAuthRoleMenus", saveAuthRoleMenus);

        serviceProviderDesc.put(AuthMenuService.class, authMenuServiceProviderDesc);
    }
}
