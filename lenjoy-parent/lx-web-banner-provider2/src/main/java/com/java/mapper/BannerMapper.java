package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2020/1/16- 15:50
 */
public interface BannerMapper {

    @Select("SELECT * FROM web_banner ORDER BY sort ASC")
    List<Map<String,Object>> getWebBanner();
}
