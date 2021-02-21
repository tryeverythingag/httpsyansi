package com.java.controller;

import com.java.service.OrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description：订单结算-自动从消息队列中获取数据，然后将订单信息等存放到数据库
 * author：jkdsk
 * date：09:34
 */
@Component
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value=@Queue(value="queue-order"),
                    exchange = @Exchange(value = "exchange-order",type="fanout")
            )
    )
    @RabbitHandler
    public void handleMQMessage(@Payload Map<String,Object> dataMap, Channel channel,@Headers Map<String,Object> headers){
        try {
            //1、对接支付宝/微信
            Thread.sleep(15000);
            //2、从消息队列("queue-order")取出dataMap数据
//                String orderNo = (String)dataMap.get("orderNo");
//                Long userId = (Long)dataMap.get("userId");
            //3、将dataMap等数据保存到数据库的订单表中--->状态已经创建
            orderService.addOrder(dataMap);
            //4、手动确认正确的从消息队列中取出数据，并且处理完毕
            Long tag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(tag,false);//消息队列确认
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
