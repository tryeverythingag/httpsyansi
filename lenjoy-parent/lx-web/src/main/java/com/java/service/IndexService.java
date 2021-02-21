package com.java.service;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/11/5- 21:58
 */
public interface IndexService {
    List<Map<String, Object>> findWebMenus(String menusType);

    List<Map<String,Object>> findZXMenu();

    void clearZxMenu();

    List<Map<String,Object>> findAllProductDetail();

    List<Map<String,Object>> findUpdatedProductDetail();

    //秒杀开始
    void processSecKill();

    //秒杀结束
    void updateEnd();

    List<Map<String, Object>> findSecKillProductList();
}
