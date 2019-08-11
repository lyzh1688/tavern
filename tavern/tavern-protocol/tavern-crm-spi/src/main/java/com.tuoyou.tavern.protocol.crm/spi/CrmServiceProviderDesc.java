package com.tuoyou.tavern.protocol.crm.spi;

import com.tuoyou.tavern.protocol.common.RequestMethods;
import com.tuoyou.tavern.protocol.common.SpiDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public class CrmServiceProviderDesc {
    public static Map<Class<?>, Map<String, SpiDescription>> serviceProviderDesc = new HashMap<>();
    static {
        //StaffService
        Map<String,SpiDescription> customServiceDescMap = new HashMap<>();
        SpiDescription customServiceQueryStaffBasicInfo = new SpiDescription.Builder()
                .serviceClass(CustomService.class)
                .requestMethod(RequestMethods.GET)
                .resourcePath("/crm/info?accnt=${accnt}&password=${password}")
                .serviceApi("getCustomInfo")
                .build();

        customServiceDescMap.put("getCustomInfo",customServiceQueryStaffBasicInfo);

        serviceProviderDesc.put(CustomService.class,customServiceDescMap);
    }
}
