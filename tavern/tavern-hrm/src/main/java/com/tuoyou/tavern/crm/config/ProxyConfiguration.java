package com.tuoyou.tavern.crm.config;

import com.tuoyou.tavern.protocol.authcenter.spi.AuthMenuService;
import com.tuoyou.tavern.protocol.authcenter.spi.AuthMenuServiceProviderDesc;
import com.tuoyou.tavern.rpc.libs.core.HttpProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/02 <br>
 */
@Configuration
public class ProxyConfiguration {

    @Bean
    public AuthMenuService authMenuService(){
        return new HttpProxy<AuthMenuService>("http://127.0.0.1:8081",AuthMenuService.class, AuthMenuServiceProviderDesc.serviceProviderDesc.get(AuthMenuService.class)).getProxy();
    }
}
