package com.java.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/10/18- 19:34
 */
public interface LoginService {
    boolean login(String username, String pwd);

    List<Map<String,Object>> findAuthorityByUsername(Map<String,Object> paramMap);
}