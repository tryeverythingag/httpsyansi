package com.java.service.impl;

import com.java.mapper.OrderMapper;
import com.java.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.service.impl
 * @ClassName: OrderServiceImpl
 * @Author: tryev
 * @Description: Mq
 * @Date: 2020/2/23 14:35
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public String sendData2MQ(Long userId,Long seckillId) {
        //1.生成订单编号
        String orderNo = UUID.randomUUID().toString();
        //2.将订单编号,用户Id保存到rabbitMQ中去
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("orderNo", orderNo);
        dataMap.put("userId", userId);
        dataMap.put("seckillId", seckillId);
        rabbitTemplate.convertAndSend("exchange-order",null,dataMap);
        return orderNo;
    }

    @Transactional(readOnly = false)
    @Override
    public void addOrder(Map<String, Object> paramMap) {
        //1.查询出秒杀价格
        Float cost = orderMapper.getSeckillPriceById((Long) paramMap.get("seckillId"));
        //2.将数据添加到数据库中去
        paramMap.put("cost", cost);
        orderMapper.insertOrder(paramMap);
    }

    @Override
    public boolean checkOrder(String orderNo) {
        //大于等于1就可以结算
        return orderMapper.checkOrder(orderNo)>=1;
    }
}
