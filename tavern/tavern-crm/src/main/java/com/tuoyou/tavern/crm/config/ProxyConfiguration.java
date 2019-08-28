package com.tuoyou.tavern.crm.config;

import com.tuoyou.tavern.protocol.hrm.spi.HrmDictServiceProviderDesc;
import com.tuoyou.tavern.protocol.hrm.spi.HrmUserDictService;
import com.tuoyou.tavern.rpc.libs.core.HttpProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
@Configuration
public class ProxyConfiguration {

    @Bean
    public HrmUserDictService hrmUserDictService(){
        return new HttpProxy<HrmUserDictService>("http://127.0.0.1:8084",HrmUserDictService.class, HrmDictServiceProviderDesc.serviceProviderDesc.get(HrmUserDictService.class)).getProxy();
    }
}
