package com.java.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description: 描述
 * Date: 2020/12/26 10:36
 * author: LT
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@EnableCaching
@MapperScan("com.java.mapper")
@EnableScheduling//开始定时器任务调度
public class SeckillProductScanStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillProductScanStartApplication.class);
    }
}
