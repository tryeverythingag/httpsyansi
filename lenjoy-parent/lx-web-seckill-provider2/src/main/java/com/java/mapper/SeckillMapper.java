package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.mapper
 * @ClassName: SeckillMapper
 * @Author: tryev
 * @Description: 提供者1
 * @Date: 2020/2/2 13:25
 * @Version: 1.0
 */
public interface SeckillMapper {

    /**
     * 根据秒杀id获取详细的商品信息
     * @param seckillId
     * @return
     */
    @Select("SELECT * FROM web_seckill WHERE id=#{arg0}")
    Map<String,Object> getProductBySeckillId(Long seckillId);
}
