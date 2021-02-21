package com.java.service;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2020/1/13- 19:10
 */
public interface BannerService {
    /**
     * 后台轮播数据
     * @return
     */
    List<Map<String,Object>> findBanner();

    boolean saveBanner(Map<String, Object> paramMap);

}
