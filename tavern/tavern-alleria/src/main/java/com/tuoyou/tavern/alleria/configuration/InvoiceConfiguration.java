package com.tuoyou.tavern.alleria.configuration;

import com.tuoyou.tavern.invoice.ocr.libs.client.TBOcrAgent;
import com.tuoyou.tavern.invoice.verify.libs.client.ZBJVerifyAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
@Configuration
@ComponentScan("com.tuoyou.tavern.common.core.exception")
public class InvoiceConfiguration {

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
