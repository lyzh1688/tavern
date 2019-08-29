package com.tuoyou.tavern.crm;

import com.dfzq.obgear.framework.spring.db.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by 刘悦之 on 2019/7/6.
 */
@SpringBootApplication
@Import({DynamicDataSourceRegister.class})
public class CrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

}
