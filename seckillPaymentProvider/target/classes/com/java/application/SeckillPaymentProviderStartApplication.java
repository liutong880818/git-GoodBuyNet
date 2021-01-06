package com.java.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Description: 描述
 * Date: 2020/12/26 10:36
 * author: LT
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@EnableCaching
@MapperScan("com.java.mapper")
public class SeckillPaymentProviderStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillPaymentProviderStartApplication.class);
    }
}
