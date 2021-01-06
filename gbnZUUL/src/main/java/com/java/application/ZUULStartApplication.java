package com.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Description: ZUUL网关
 * Date: 2020/11/11 22:11
 * author: LT
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy//开启zuul网关代理
public class ZUULStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZUULStartApplication.class);
    }
}
