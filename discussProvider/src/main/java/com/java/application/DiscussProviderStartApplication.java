package com.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * time:09:25
 * author:丁鹏
 */
@SpringBootApplication(scanBasePackages = "com.java.*",exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableDiscoveryClient
public class DiscussProviderStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscussProviderStartApplication.class);
    }
}
