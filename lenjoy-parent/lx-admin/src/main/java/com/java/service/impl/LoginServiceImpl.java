package com.java.service.impl;

import com.java.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:01
 */
@Service
public class LoginServiceImpl implements com.java.service.LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public boolean login(String username, String pwd){
        return loginMapper.login(username,pwd)>=1;
    }

    @Transactional(readOnly = false)
    @Override
    public List<Map<String, Object>> findAuthorityByUsername(Map<String,Object> paramMap) {
        String isRoot = loginMapper.getRootByUsername((String) paramMap.get("username"));
        paramMap.put("isRoot", isRoot);
        return loginMapper.getAuthorityByUsername(paramMap);
    }

}

