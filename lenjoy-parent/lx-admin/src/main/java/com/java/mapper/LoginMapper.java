package com.java.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/10/18- 19:25
 */
public interface LoginMapper {

    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @return
     */
    @Select("SELECT COUNT(*) FROM admin_users WHERE username=#{username} AND pwd=#{pwd}")
    int login(@Param("username") String username, @Param("pwd") String pwd);


    /**
     * 根据用户名获取对应的权限
     * @param username
     * @return
     */
    List<Map<String,Object>> getAuthorityByUsername(Map<String,Object> paramMap);

    /**
     * 根据用户名获取角色状态码
     * @param username
     * @return
     */
    @Select("SELECT isRoot from admin_users WHERE username=#{param}")
    String getRootByUsername(String username);
}
