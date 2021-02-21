package com.java.mapper;

import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/10/22- 22:50
 */
public interface WebMenuMapper {

    /**
     * 分页查询前台横向导航栏数据
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Select("SELECT id,title,url,DATE_FORMAT(updateTime,'%Y-%m-%d %H:%i:%S') AS updateTime FROM web_menu WHERE menuType='1' LIMIT #{param1},#{param2}")
    List<Map<String,Object>> selectWebMenu(Integer startIndex,Integer pageSize);
    /**
     * 获取web_menu表中的所有数据
     * @return
     */
    @Select("select count(*) from web_menu")
    int countWebMenu();

    /**
     * 添加前台导航栏信息
     * @param paramMap
     * @return
     */
    @Insert("INSERT INTO web_menu VALUE(NULL,#{title},#{url},#{menuType},NOW())")
    int insertWebMenu(Map<String,Object> paramMap);

    /**
     * 修改前台横向菜单
     * @param paramMap
     * @return
     */
    @Update("UPDATE web_menu SET title=#{title},url=#{url},updateTime=NOW() WHERE id=#{id}")
    int updateWebMenu(Map<String,Object> paramMap);

    /**
     * 批量删除
     * @param paramMap
     * @return
     */
    @Delete("DELETE FROM web_menu WHERE id IN(${idStr})")
    int batchDelWebMenu(Map<String,Object> paramMap);


}
