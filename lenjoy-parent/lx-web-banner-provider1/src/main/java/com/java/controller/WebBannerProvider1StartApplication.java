package com.java.controller;

import com.java.service.BannerService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2020/1/15- 11:24
 */
@SpringBootApplication(scanBasePackages = {"com.java.controller","com.java.service.impl"})
@EnableEurekaClient
@EnableDiscoveryClient
@Controller
@MapperScan(basePackages = "com.java.mapper")
public class WebBannerProvider1StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebBannerProvider1StartApplication.class);
    }


    @Autowired
    private BannerService bannerService;

    @RequestMapping("/getWebBanners.do")
    public @ResponseBody List<Map<String,Object>> getWebBanners(){
        return bannerService.findWebBanner();
    }
}
