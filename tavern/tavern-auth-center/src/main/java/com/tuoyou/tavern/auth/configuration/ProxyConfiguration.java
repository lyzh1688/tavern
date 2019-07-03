package com.tuoyou.tavern.auth.configuration;

import com.tuoyou.tavern.protocol.hrm.spi.HrmServiceProviderDesc;
import com.tuoyou.tavern.protocol.hrm.spi.StaffService;
import com.tuoyou.tavern.rpc.libs.core.HttpProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
@Configuration
public class ProxyConfiguration {

    @Bean
    public StaffService staffService(){
        return new HttpProxy<StaffService>("http://127.0.0.1:8082",StaffService.class, HrmServiceProviderDesc.serviceProviderDesc.get(StaffService.class)).getProxy();
    }
}
