package com.java.service;

import java.util.Map;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.service
 * @ClassName: OrderService
 * @Author: tryev
 * @Description: MQ
 * @Date: 2020/2/23 14:32
 * @Version: 1.0
 */
public interface OrderService {
    String sendData2MQ(Long userId,Long seckillId);

    void addOrder(Map<String, Object> paramMap);

    boolean checkOrder(String orderNo);
}
