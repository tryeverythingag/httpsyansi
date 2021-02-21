package com.java.service.impl;

import com.java.mapper.IndexMapper;
import org.mockito.verification.Timeout;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ron
 * @data 2019/11/5- 21:50
 */
@CacheConfig(cacheNames = {"IndexServiceImpl_"})//key前面的前缀,业务里有缓冲都加
@Service
public class IndexServiceImpl implements com.java.service.IndexService {

    @Resource
    private IndexMapper indexMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Map<String, Object>> findWebMenus(String menuType) {
        try {
            ValueOperations vps = redisTemplate.opsForValue();
            Object obj = vps.get("hxWebMenu");
            if (obj == null) {
                List<Map<String, Object>> menuList = indexMapper.selectWebMenus(menuType);
                //将从mysql中查询的数据重新存入reis中去
                vps.set("hxWebMenu",menuList);
                //设置失效时间
                redisTemplate.expire("hxWebMenu", 5 , TimeUnit.MINUTES);
                return menuList;
            }
            return (List<Map<String, Object>>)obj;
        } catch (Exception e) {
            List<Map<String, Object>> menuList = indexMapper.selectWebMenus(menuType);
            System.out.println("IndexServiceImpl...findWebMenus...Redis忘记开启了");
            return menuList;
        }
    }

    @Cacheable(key="'findZXWeb'")
    @Override
    public List<Map<String,Object>> findZXMenu() {
        return indexMapper.selectWebMenus("1");
    }

    @CacheEvict(key="'clearZxMenu'")
    @Override
    public void clearZxMenu() {
        System.out.println("再redis中的纵向导航栏数据已经被清空了");
    }

    @Override
    public List<Map<String, Object>> findAllProductDetail() {
        //1.获取商品的详情,但是不包括商品的图片
        List<Map<String, Object>> productDetailList = indexMapper.getAllProductDetail();
        //2.再获取图片
        for (int i = 0;i<productDetailList.size(); i++){
            Long productId = (Long) productDetailList.get(i).get("id");
            List<String> imgUrlList = indexMapper.getProductImgUrls(productId);
            productDetailList.get(i).put("imgUrlList", imgUrlList);
        }
        return productDetailList;
    }

    @Override
    public List<Map<String, Object>> findUpdatedProductDetail() {
        //1.获取最近5分钟被修改的商品的详情,但是不包括商品的图片
        List<Map<String, Object>> productDetailList1 = indexMapper.getUpdatedProductDetail();
        //2.再获取图片
        for (int i = 0;i<productDetailList1.size(); i++){
            Long productId = (Long) productDetailList1.get(i).get("id");
            List<String> imgUrlList = indexMapper.getProductImgUrls(productId);
            productDetailList1.get(i).put("imgUrlList", imgUrlList);
        }
        return productDetailList1;
    }

    @Override
    public void processSecKill() {
        ListOperations lot = redisTemplate.opsForList();
        //1.查询出秒杀表中所有即将开始的秒杀产品
        List<Map<String, Object>> secKillNoStartList = indexMapper.getSecKillNoStart();
        //2.将秒杀产品在redis数据库中记录下来
        for(Map<String,Object> tempMap:secKillNoStartList){
            //获取秒杀表主键
            Long id = (Long) tempMap.get("id");
            //获取参与秒杀的商品id
            Long productId = (Long) tempMap.get("productId");
            //构建key
            String key = "seckill_product_"+id;
            //将num数量的秒杀名额对应的商品id存放到redis中去
            Integer num = (Integer) tempMap.get("num");
            for (int i = 0; i < num; i++) {
                lot.rightPush(key,productId);
            }
            //3.修改秒杀产品的状态
            indexMapper.updateSecKillStatus("1",id);
        }
    }

    @Override
    public void updateEnd() {
        List<Map<String, Object>> endProductList = indexMapper.getEndSecKill();
        //1.删除掉redis中空的秒杀商品List集合
        for (Map<String, Object> tempMap : endProductList) {
            //获取秒杀id
            Long endSecKillId = (Long) tempMap.get("id");
            redisTemplate.delete("seckill_product_"+endSecKillId);
            //2.修改产品状态
            indexMapper.updateSecKillStatus("2",endSecKillId);
        }
    }

    @Override
    public List<Map<String, Object>> findSecKillProductList() {
        //1.先获取所有的秒杀商品
        List<Map<String, Object>> secKillProductList = indexMapper.getSecKillProductList();
        //2.获取商品的图片
        for (int i = 0;i<secKillProductList.size(); i++){
            Long productId = (Long) secKillProductList.get(i).get("id");
            List<String> imgUrlList = indexMapper.getProductImgUrls(productId);
            secKillProductList.get(i).put("imgUrlList", imgUrlList);
        }
        return secKillProductList;
    }
}
