package com.java.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2020/1/13- 18:57
 */
public interface BannerMapper {

    /**
     * 查询后台轮播数据
     * @return
     */
    @Select("select * from web_banner limit 6")
    List<Map<String, Object>> getBanners();

    /**
     * 添加轮播图片
     * @param paramMap
     * @return
     */
    @Insert("INSERT INTO web_banner VALUES(NULL,#{imgUrl},#{href},#{remark},#{sort},NOW())")
    int insertBanner(Map<String, Object> paramMap);

}
