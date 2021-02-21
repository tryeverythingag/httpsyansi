package com.java.controller;

import com.java.service.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.controller
 * @ClassName: WebSeckillConsumerStartApplication
 * @Author: tryev
 * @Description: 消费者
 * @Date: 2020/2/3 11:09
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableDiscoveryClient
@Controller
@MapperScan(basePackages = "com.java.mapper")
@ServletComponentScan(basePackages = "com.java.filters")
public class WebSeckillConsumerStartApplication {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(WebSeckillConsumerStartApplication.class);
    }

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;


    @RequestMapping("/doSecKillByConsumer.do")
    @ResponseBody
    public Map<String,Object> doSecKillByConsumer(Long seckillId,Long userId){
        //1.处理秒杀模块
        Map<String,Object> resultMap = restTemplate.getForObject("http://lx-web-seckill-provider//processSeckill/"+seckillId+"/"+userId, Map.class);
        //2.往消息队列中存放数据:订单编号,存放用户唯一标识符(userId);
        if ("0".equals(resultMap.get("status"))) {
            String orderNo = orderService.sendData2MQ(userId,seckillId);
            resultMap.put("orderNo", orderNo);
        }
        return resultMap;
    }

    /**
     * 确认订单是否处理完毕
     * @param orderNo
     * @return
     */
    @RequestMapping("/checkOrder.do")
    @ResponseBody
    public boolean checkOrder(String orderNo) {
        return orderService.checkOrder(orderNo);
    }
}
