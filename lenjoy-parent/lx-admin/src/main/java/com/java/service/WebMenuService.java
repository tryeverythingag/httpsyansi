package com.java.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/10/22- 23:16
 */
public interface WebMenuService {
   Map<String, Object> findWebMenu(@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

   boolean saveWebMenu(Map<String, Object> paramMap);

   boolean updateWebMenu(Map<String,Object> paramMap);

   boolean batchDelWebMenu(Map<String,Object> paramMap);
}
