package com.tuoyou.tavern.gateway.config;

import com.tuoyou.tavern.gateway.filter.DoAfterLogin;
import com.tuoyou.tavern.gateway.filter.DoAuthority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 刘悦之 on 2019/7/6.
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public DoAuthority doAuthority(){
        return new DoAuthority();
    }

    @Bean
    public DoAfterLogin doAfterLogin(){
        return new DoAfterLogin();
    }
}
