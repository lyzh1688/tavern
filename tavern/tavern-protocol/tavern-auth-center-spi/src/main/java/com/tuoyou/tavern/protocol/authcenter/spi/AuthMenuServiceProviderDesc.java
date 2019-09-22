package com.tuoyou.tavern.protocol.authcenter.spi;

import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuResponse;
import com.tuoyou.tavern.protocol.common.RequestMethods;
import com.tuoyou.tavern.protocol.common.SpiDescription;
import com.tuoyou.tavern.protocol.common.annotation.PathParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
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

        SpiDescription queryAuthMenuPermissionByRole = new SpiDescription.Builder()
                .serviceClass(AuthMenuService.class)
                .requestMethod(RequestMethods.GET)
                .resourcePath("/menu/findPermissions?roleId=${roleId}")
                .serviceApi("queryAuthMenuPermissionByRole")
                .build();

        authMenuServiceProviderDesc.put("queryAuthMenuByRole", queryAuthMenuByRole);
        authMenuServiceProviderDesc.put("queryAuthMenuPermissionByRole", queryAuthMenuPermissionByRole);
        authMenuServiceProviderDesc.put("saveAuthRoleMenus", saveAuthRoleMenus);

        serviceProviderDesc.put(AuthMenuService.class, authMenuServiceProviderDesc);
    }
}
