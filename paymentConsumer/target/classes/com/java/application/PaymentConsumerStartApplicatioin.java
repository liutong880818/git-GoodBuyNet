package com.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Description: 描述
 * Date: 2020/12/28 14:45
 * author: LT
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@EnableDiscoveryClient
@ServletComponentScan("com.java.filters")
public class PaymentConsumerStartApplicatioin {

    @Bean //注入Ribbon的核心类（即，把创建对象交给spring容器）
    @LoadBalanced //创建Ribbon负载均衡对象：RestTemplate
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentConsumerStartApplicatioin.class);
    }
}
