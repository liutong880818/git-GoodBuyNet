package com.java.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Description: 描述
 * Date: 2020/10/28 15:01
 * author: LT
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@MapperScan(basePackages = "com.java.mapper")
@ServletComponentScan(basePackages = "com.java.filters")
@EnableCaching//开启缓存(redis)
public class GBNAdminStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(GBNAdminStartApplication.class);
    }
}
