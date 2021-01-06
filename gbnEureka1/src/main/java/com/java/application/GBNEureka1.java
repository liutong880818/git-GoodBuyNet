package com.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Description: 描述
 * Date: 2020/10/28 10:41
 * author: LT
 */
@SpringBootApplication
@EnableEurekaServer
public class GBNEureka1 {

    public static void main(String[] args) {
        SpringApplication.run(GBNEureka1.class);
    }

}
