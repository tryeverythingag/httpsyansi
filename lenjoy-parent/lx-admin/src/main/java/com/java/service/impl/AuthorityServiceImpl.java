package com.java.service.impl;

import com.java.mapper.AuthorityMapper;
import com.java.pojo.admin.OneMenu;
import com.java.service.AuthorityService;
import com.java.utils.MD5Tool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/11/29- 17:23
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    public List<Map<String, Object>> findSystemUser() {
        return authorityMapper.getSystemUser();
    }

    @Override
    public List<Map<String, Object>> findAuthority(Long parentId) {
        return authorityMapper.getAuthority(parentId);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveSystemUser(String username, String pwd, String menuIds) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("pwd", MD5Tool.MD5(pwd));
        int flag = authorityMapper.addSystemUser(paramMap);
        if (flag >= 1) {
            Long userId = (Long) paramMap.get("userId");
            String[] menuIdAttr = menuIds.split("\\,");
            for (String menuId : menuIdAttr) {
                int flag2 = authorityMapper.addUserAuthority(userId, Long.parseLong(menuId));
                if (flag2 <= 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<OneMenu> findAuthorityByRelation() {
        return authorityMapper.getAuthorityByRelation();
    }

    @Override
    public List<Long> findOwnAuthorityId(Long userId) {
        return authorityMapper.getOwnAuthorityId(userId);
    }
}