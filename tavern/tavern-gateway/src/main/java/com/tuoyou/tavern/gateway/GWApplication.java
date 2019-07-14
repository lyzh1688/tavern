package com.tuoyou.tavern.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Spring boot app.
 *
 * @author shuaicj 2017/10/18
 */
@SpringBootApplication
@EnableZuulProxy
@CrossOrigin(origins = "http://localhost:8090")
public class GWApplication {

    public static void main(String[] args) {

        SpringApplication.run(GWApplication.class, args);
    }
}
