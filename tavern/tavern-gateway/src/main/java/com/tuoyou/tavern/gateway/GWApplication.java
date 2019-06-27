package com.tuoyou.tavern.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Spring boot app.
 *
 * @author shuaicj 2017/10/18
 */
@SpringBootApplication
@EnableZuulProxy
public class GWApplication {

    public static void main(String[] args) {

        SpringApplication.run(GWApplication.class, args);
    }
}
