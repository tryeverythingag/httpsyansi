package com.java.mapper;

import com.java.pojo.admin.OneMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/11/29- 17:07
 */
public interface AuthorityMapper {

    /**
     * 查看所有系统用户信息
     * @return
     */
    @Select("SELECT * FROM admin_users")
    List<Map<String,Object>> getSystemUser();

    /**
     * 获取权限
     * @param parentId
     * @return
     */
    @Select("SELECT * FROM admin_menus WHERE parentId=#{arg0} AND flag!='1'")
    List<Map<String,Object>> getAuthority(Long parentId);

    /**
     * 添加系统用户信息
     * @return
     */
    @Options(keyProperty = "userId",useGeneratedKeys = true,keyColumn = "id")
    @Insert("INSERT INTO admin_users VALUES(NULL,#{username},#{pwd},'0',NOW())")
    int addSystemUser(Map<String,Object> paramMap);

    /**
     * 添加用户与权限的对应关系
     * @param userId
     * @param menuId
     * @return
     */
    @Insert("INSERT INTO admin_user_authority VALUES(#{param1},#{param2})")
    int addUserAuthority(Long userId,Long menuId);

    /**
     * 获取权限信息，通过关联关系描述
     * @return
     */
    List<OneMenu> getAuthorityByRelation();

    /**
     * 根据用户id查询出用户拥有的权限id
     * @param userId
     * @return
     */
    @Select("SELECT aua.menuId FROM `admin_user_authority` aua \n" +
            "INNER JOIN `admin_users` au ON aua.userId\n" +
            "=au.id WHERE au.id=#{arg0}")
    List<Long> getOwnAuthorityId(Long userId);
}
