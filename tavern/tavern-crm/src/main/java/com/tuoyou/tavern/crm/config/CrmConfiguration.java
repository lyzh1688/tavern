package com.tuoyou.tavern.crm.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
@Configuration
public class CrmConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("50MB");
        /// 总上传数据大小
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }

}
