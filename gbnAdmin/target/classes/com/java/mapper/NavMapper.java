package com.java.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description: 前台菜单导航栏
 * Date: 2020/11/4 14:14
 * author: LT
 */
@Repository
public interface NavMapper {

    /**
     * 查询导航栏的总记录数
     * @return
     */
    @Select("SELECT COUNT(*) FROM web_menu WHERE menuType=#{arg0}")
    int selectTotalCount(String menuType);

    /**
     * 分页查询前台导航栏信息
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Select("SELECT * FROM web_menu LIMIT #{startIndex},#{pageSize}")
    List<Map<String,Object>> selectNavs(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 添加一条横向导航栏信息
     * @param title
     * @param url
     * @return
     */
    @Insert("INSERT INTO web_menu SET title=#{title},url=#{url},menuType=#{menuType},updateTime=NOW()")
    int insertNav(@Param("title") String title,@Param("url") String url,@Param("menuType") String menuType);

    /**
     * 根据id查询某个指定横向导航栏信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM web_menu WHERE id=#{arg0}")
    Map<String,Object> selectTransverseNavById(String id);

    /**
     * 根据id修改某个指定横向导航栏信息
     * @param title
     * @param url
     * @param id
     * @return
     */
    @Update("UPDATE web_menu SET title=#{title},url=#{url},updateTime=NOW() WHERE id=#{id}")
    int updateTransverseNavById(@Param("title") String title,@Param("url")String url,@Param("id")String id);

    /**
     * 根据id删除某个指定横向导航栏信息
     * @param paramMap
     * @return
     */
    @Delete("DELETE FROM web_menu WHERE id IN (${idStr})")
    int deleteTransverseNavById(Map<String,Object> paramMap);

}
