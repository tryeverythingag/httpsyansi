package com.java.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ron
 * @data 2019/10/16- 20:25
 */
@SpringBootApplication(scanBasePackages = {"com.java.controller","com.java.service.impl"})
@MapperScan(basePackages = "com.java.mapper")
@EnableEurekaClient//开启注册中心客户端
public class AdminStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminStartApplication.class);
    }
}
