package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/11/5- 21:42
 */
public interface IndexMapper {

    /**
     * 查询横向或者纵向导航栏
     *
     * @param menuType
     * @return
     */
    @Select("SELECT * FROM web_menu WHERE menuType=#{arg0} ORDER BY updateTime ASC LIMIT 8")
    List<Map<String, Object>> selectWebMenus(String menuType);

    /**
     * 查询所有商品的信息
     *
     * @return
     */
    @Select("select wpd.*,ws.sortName FROM web_product_detail wpd INNER JOIN web_sort ws ON\n" +
            "wpd.typeId=ws.id")
    List<Map<String, Object>> getAllProductDetail();

    /**
     * 根据商品id获取商品图片
     *
     * @param productId
     * @return
     */
    @Select("SELECT wpi.imgUrl FROM web_product_detail wpd INNER JOIN web_product_img wpi \n" +
            "ON wpd.id=#{arg0}")
    List<String> getProductImgUrls(Long productId);

    /**
     * 查询出5分钟内被修改过的商品信息
     *
     * @return
     */
    @Select("SELECT wpd.*,ws.sortName FROM web_product_detail wpd INNER JOIN web_sort ws \n" +
            "ON wpd.typeId=ws.id WHERE wpd.updateTime>=NOW()-INTERVAL 5 MINUTE")
    List<Map<String, Object>> getUpdatedProductDetail();

    /**
     * 查询出即将开始秒杀的商品
     *
     * @return
     */
    @Select("SELECT * FROM web_seckill WHERE startTime<=NOW() AND NOW()<=endTime AND status=0")
    List<Map<String, Object>> getSecKillNoStart();

    /**
     * 修改秒杀状态,如果秒杀完状态就改成2
     *
     * @param status
     * @param seckillId
     * @return
     */
    @Update("UPDATE web_seckill SET `status`=#{param1} WHERE id=#{param2}")
    int updateSecKillStatus(String status, Long seckillId);

    /**
     * 秒杀结束
     *
     * @return
     */
    @Select("SELECT * FROM web_seckill WHERE endTime<NOW() AND status='1'")
    List<Map<String, Object>> getEndSecKill();

    /**
     * 获取秒杀商品列表
     * @return
     */
    @Select("SELECT wpd.*,ws.id AS seckillId,ws.seckillPrice,ws.status,ws.num,ws.endTime,ws.href AS seckillHref\n" +
            "FROM web_seckill ws INNER JOIN web_product_detail wpd\n" +
            "ON ws.productId=wpd.id WHERE ws.status!='2'")
    List<Map<String, Object>> getSecKillProductList();

}
