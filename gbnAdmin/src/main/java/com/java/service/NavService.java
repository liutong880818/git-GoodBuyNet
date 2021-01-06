package com.java.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/11/4 14:47
 * author: LT
 */
public interface NavService {
    /**
     * 获取分页横向导航栏
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String,Object> findNavs(int pageNum, int pageSize);

    /**
     * 添加一条横向导航栏信息
     * @param title
     * @param url
     * @return
     */
    Map<String,Object> saveNav(String title,String url);

    /**
     * 根据id查询某个指定横向导航栏信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM web_menu WHERE id=#{arg0}")
    Map<String,Object> findTransverseNavById(String id);

    /**
     * 根据id修改某个指定横向导航栏信息
     * @param title
     * @param url
     * @param id
     * @return
     */
    Map<String,Object> modifyTransverseNavById(String title,String url,String id);

    /**
     * 根据id删除某个指定横向导航栏信息
     * @param idStr
     * @return
     */
    Map<String,Object> removeTransverseNavById(String idStr);
}
