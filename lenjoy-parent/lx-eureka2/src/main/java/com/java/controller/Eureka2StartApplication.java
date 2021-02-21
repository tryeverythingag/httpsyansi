package com.java.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ron
 * @data 2019/11/18- 21:01
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka2StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(Eureka2StartApplication.class);
    }
}
