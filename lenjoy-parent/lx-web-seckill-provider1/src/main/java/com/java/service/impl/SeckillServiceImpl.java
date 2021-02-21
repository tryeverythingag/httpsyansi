package com.java.service.impl;

import com.java.exceptions.SeckillException;
import com.java.mapper.SeckillMapper;
import com.java.service.SeckillService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.service.impl
 * @ClassName: SeckillServiceImpl
 * @Author: tryev
 * @Description: 业务
 * @Date: 2020/2/2 13:32
 * @Version: 1.0
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private SeckillMapper seckillMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void doSeckill(Long seckillId, Long userId) throws SeckillException {
        //判断商品的状态是否已经开始x
        Map<String, Object> productMap = seckillMapper.getProductBySeckillId(seckillId);
        //秒杀商品不存在
        if (productMap == null) {
            throw new SeckillException("秒杀商品不存在");
        }
        String status = (String) productMap.get("status");
        //秒杀产品未开始
        if ("0".equals(status)) {
            throw new SeckillException("秒杀还未开始,请稍后...");
        }
        //秒杀已经结束
        if ("2".equals(status)) {
            throw new SeckillException("秒杀已经结束了,下次再来吧...");
        }
        //秒杀还在进行
        ListOperations lop = redisTemplate.opsForList();
        Long productId = (Long) lop.leftPop("seckill_product_" + seckillId);
        //判断商品是否已经被抢购完了
        if (productId == null) {
            throw new SeckillException("商品已经被抢购一空,下次再来吧");
        }else{//还有商品可以抢购
            SetOperations sop = redisTemplate.opsForSet();
            Boolean flag = sop.isMember("seckill_users_" + seckillId, userId);
            //用户已经抢购过
            if (flag) {
                lop.rightPush("seckill_product_" + seckillId,productId);
                throw new SeckillException("秒杀名额只有一个,不能重复抢购,下次再来吧");
            }else{
                System.out.println("SeckillServiceImpl........成功抢购一个名额");
                sop.add("seckill_users_" + seckillId, userId);
            }
        }
    }
}
