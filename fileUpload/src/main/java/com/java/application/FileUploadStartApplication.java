package com.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Description: 描述
 * Date: 2020/12/21 22:34
 * author: LT
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@EnableDiscoveryClient
public class FileUploadStartApplication {

    public static void main(String[] args){
        SpringApplication.run(FileUploadStartApplication.class);
    }

}
