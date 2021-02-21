package com.java.service.impl;

import com.java.mapper.WebMenuMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ron
 * @data 2019/10/22- 23:08
 */
@Service
public class WebMenuServiceImpl implements com.java.service.WebMenuService {
    @Resource
    private WebMenuMapper webMenuMapper;

    /**
     * 分页查询前台横向导航栏数据
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public Map<String, Object> findWebMenu(@Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize){
        Map<String, Object> resultMap = new HashMap<>();
        //1.调用dao层获取分页后的数据.
        List<Map<String, Object>> menuList = webMenuMapper.selectWebMenu(startIndex, pageSize);
        //2.调用dao层获取web_menu表中的总记录数.
        System.out.println("menuList="+menuList);
        int i = webMenuMapper.countWebMenu();
        resultMap.put("rows", menuList);
        resultMap.put("total",i);
        return resultMap;
    }

    public boolean saveWebMenu(Map<String, Object> paramMap) {
        return webMenuMapper.insertWebMenu(paramMap)>=1;
    }

    @Override
    public boolean updateWebMenu(Map<String, Object> paramMap) {
        return webMenuMapper.updateWebMenu(paramMap)>=1;
    }

    @Override
    public boolean batchDelWebMenu(Map<String, Object> paramMap) {
        return webMenuMapper.batchDelWebMenu(paramMap)>=1;
    }
}
