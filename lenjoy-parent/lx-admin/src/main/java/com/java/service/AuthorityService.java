package com.java.service;

import com.java.pojo.admin.OneMenu;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/11/29- 17:20
 */
public interface AuthorityService {

    List<Map<String,Object>> findSystemUser();

    List<Map<String,Object>> findAuthority(Long parentId);

    boolean saveSystemUser(String username,String pwd,String menuIds) throws Exception;

    List<OneMenu> findAuthorityByRelation();

    List<Long> findOwnAuthorityId(Long userId);
}
