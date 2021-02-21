package com.java.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.mapper
 * @ClassName: OrderMapper
 * @Author: tryev
 * @Description: 订单
 * @Date: 2020/2/23 21:19
 * @Version: 1.0
 */
public interface OrderMapper {

    /**
     * 添加订单到数据库中去
     * @param paramMap
     * @return
     */
    @Insert("INSERT INTO web_order VALUES(NULL,#{orderNo},#{userId},'0',#{cost})")
    int insertOrder(Map<String, Object> paramMap);

    /**
     * 根据秒杀id获取秒杀产品的价格
     * @param seckillId
     * @return
     */
    @Select("SELECT seckillPrice FROM web_seckill where id=#{arg0}")
    Float getSeckillPriceById(Long seckillId);

    /**
     * 确认订单是否可以支付了
     * @param orderNo
     * @return
     */
    @Select("SELECT count(*) FROM web_order WHERE orderNo=#{arg0}")
    int checkOrder(String orderNo);

}
