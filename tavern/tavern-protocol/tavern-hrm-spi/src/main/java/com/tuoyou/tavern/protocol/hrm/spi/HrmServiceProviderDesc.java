package com.tuoyou.tavern.protocol.hrm.spi;

import com.tuoyou.tavern.protocol.common.RequestMethods;
import com.tuoyou.tavern.protocol.common.SpiDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
public class HrmServiceProviderDesc {
    public static Map<Class<?>,Map<String,SpiDescription>> serviceProviderDesc = new HashMap<>();
    static {
        //StaffService
        Map<String,SpiDescription> staffServiceDescMap = new HashMap<>();
        SpiDescription staffServiceQueryStaffBasicInfo = new SpiDescription.Builder()
                .serviceClass(StaffService.class)
                .requestMethod(RequestMethods.GET)
                .resourcePath("/hrm/staff?accnt=${accnt}&password=${password}")
                .serviceApi("queryStaffBasicInfo")
                .build();

        staffServiceDescMap.put("queryStaffBasicInfo",staffServiceQueryStaffBasicInfo);

        serviceProviderDesc.put(StaffService.class,staffServiceDescMap);
    }
}