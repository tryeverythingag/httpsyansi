package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2020/1/15- 11:58
 */
@SpringBootApplication(scanBasePackages = "com.java.controller")
@EnableEurekaClient
@EnableDiscoveryClient
@Controller
//开启过滤器扫描
@ServletComponentScan(basePackages = "com.java.filters")
public class WebBannerConsumerStartApplication {

    //注入负载均衡的工具类：RestTemplate，Rest调用  new RestTemplate();
    @Bean
    @LoadBalanced//开启负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebBannerConsumerStartApplication.class);
    }

    @Autowired
    private RestTemplate restTemplate;
    /**
     */
    @RequestMapping("/findBannersByConsumer.do")
    public @ResponseBody List<Map<String,Object>> findBannersByConsumer(){
        System.out.println("WebBannerConsumerStartApplication----------进入了");
        return restTemplate.getForObject("http://lx-web-banner-provider/getWebBanners.do",List.class);
    }
}

