package com.tuoyou.tavern.protocol.hrm.spi;

import com.tuoyou.tavern.protocol.common.RequestMethods;
import com.tuoyou.tavern.protocol.common.SpiDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/8/28 <br>
 */
public class HrmDictServiceProviderDesc {
    public static Map<Class<?>, Map<String,SpiDescription>> serviceProviderDesc = new HashMap<>();
    static {
        //StaffService
        Map<String,SpiDescription> hrmDictServiceProviderDescMap = new HashMap<>();
        SpiDescription queryStaffByRole = new SpiDescription.Builder()
                .serviceClass(StaffService.class)
                .requestMethod(RequestMethods.GET)
                .resourcePath("/hrm/dict/findDictByRole?roleId=${roleId}")
                .serviceApi("queryStaffByRole")
                .build();

        hrmDictServiceProviderDescMap.put("queryStaffByRole",queryStaffByRole);

        serviceProviderDesc.put(HrmUserDictService.class,hrmDictServiceProviderDescMap);
    }
}
