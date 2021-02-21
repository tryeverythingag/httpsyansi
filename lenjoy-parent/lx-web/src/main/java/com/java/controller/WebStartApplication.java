package com.java.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @author ron
 * @data 2019/11/4- 20:06
 */
@SpringBootApplication(scanBasePackages = {"com.java.controller", "com.java.service.impl","com.java.tasks"})
@MapperScan(basePackages = "com.java.mapper")
@EnableCaching //开启缓冲
@EnableEurekaClient
@ServletComponentScan(basePackages = "com.java.filters")
@EnableScheduling
public class WebStartApplication {

    //注入负载均衡的工具类：RestTemplate，Rest调用  new RestTemplate();
    @Bean
    @LoadBalanced//开启负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(WebStartApplication.class);
    }
}
