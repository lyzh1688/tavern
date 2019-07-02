package com.tuoyou.tavern.alleria.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/02 <br>
 */
@Configuration
@MapperScan("com.tuoyou.tavern.alleria.dao")
public class MyBatisPlusConfiguration {
    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
