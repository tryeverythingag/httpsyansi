package com.java.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author ron
 * @data 2019/11/29- 12:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy //开启网关
@EnableDiscoveryClient//查找负载均衡的客户端
public class ZuulStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulStartApplication.class);
    }
}
