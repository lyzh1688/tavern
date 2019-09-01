package com.tuoyou.tavern.protocol.hrm.spi;

import com.tuoyou.tavern.protocol.common.RequestMethods;
import com.tuoyou.tavern.protocol.common.SpiDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
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
