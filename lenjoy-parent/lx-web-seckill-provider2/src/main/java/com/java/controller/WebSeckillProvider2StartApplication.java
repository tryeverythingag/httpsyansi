package com.java.controller;

import com.java.exceptions.SeckillException;
import com.java.service.SeckillService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.controller
 * @ClassName: WebSeckillProvider2StartApplication
 * @Author: tryev
 * @Description: 提供者2
 * @Date: 2020/2/3 10:52
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@MapperScan(basePackages = "com.java.mapper")
@EnableDiscoveryClient
@Controller
public class WebSeckillProvider2StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSeckillProvider2StartApplication.class);
    }

    @Resource
    private SeckillService seckillService;

    /**
     * 秒杀业务提供者1
     * @param seckillId
     * @param userId
     * @return
     */
    @RequestMapping("/processSeckill/{seckillId}/{userId}")
    @ResponseBody
    public Map<String,Object> processSeckill(@PathVariable(name="seckillId") Long seckillId, @PathVariable(name="userId") Long userId){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            seckillService.doSeckill(seckillId,userId);
            //抢购成功
            resultMap.put("status", "0");
            return resultMap;
        } catch (SeckillException e) {
            //抢购失败
            resultMap.put("status", "1");
            resultMap.put("msg",e.getMessage());
            return resultMap;
        }
    }
}
