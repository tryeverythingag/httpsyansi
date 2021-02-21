package com.java.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ron
 * @data 2019/11/18- 19:22
 */
@SpringBootApplication
@EnableEurekaServer//开启eureka服务器
public class Eureka1StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(Eureka1StartApplication.class);
    }
}
