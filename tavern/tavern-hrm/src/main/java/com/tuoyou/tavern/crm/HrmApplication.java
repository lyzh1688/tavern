package com.tuoyou.tavern.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@SpringBootApplication
@ComponentScan("com.tuoyou.tavern.common.core.exception")
public class HrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrmApplication.class, args);
    }
}
