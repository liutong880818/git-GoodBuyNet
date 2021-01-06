package com.java.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/10/28 14:31
 * author: LT
 */
public interface LoginService {
    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    Map<String,Object> findLogin(String username, String password);

    /**
     * 查询登录的用户对应拥有的菜单栏信息
     * @param id
     * @return
     */
    List<Map<String,Object>> findMenus(String id);
}
