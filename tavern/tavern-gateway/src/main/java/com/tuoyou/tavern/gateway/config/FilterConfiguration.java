package com.tuoyou.tavern.gateway.config;

import com.tuoyou.tavern.gateway.filter.DoAfterLogin;
import com.tuoyou.tavern.gateway.filter.DoAuthority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

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

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(18000L);
        config.addExposedHeader("Authorization");
        config.setExposedHeaders(Collections.singletonList("Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
