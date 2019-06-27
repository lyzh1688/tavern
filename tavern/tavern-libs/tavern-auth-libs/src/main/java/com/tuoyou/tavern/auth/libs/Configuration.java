package com.tuoyou.tavern.auth.libs;

import org.springframework.context.annotation.Bean;

/**
 * Created by 刘悦之 on 2019/6/27.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public JwtAuthenticationProperty jwtAuthenticationProperty(){
        return new JwtAuthenticationProperty();
    }
}
