package com.java.service;

import com.java.exceptions.SeckillException;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.service
 * @ClassName: SeckillService
 * @Author: tryev
 * @Description:
 * @Date: 2020/2/2 15:05
 * @Version: 1.0
 */
public interface SeckillService {
    void doSeckill(Long seckillId, Long userId) throws SeckillException;
}
