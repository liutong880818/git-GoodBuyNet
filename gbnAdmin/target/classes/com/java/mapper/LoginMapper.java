package com.java.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/10/28 12:51
 * author: LT
 */
@Repository
public interface LoginMapper {

    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT COUNT(*) FROM admin_users WHERE username=#{username} AND pwd=#{password}")
    int selectLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 查询登录的用户对应拥有的菜单栏信息
     * @param username
     * @param id
     * @return
     */
    @Select("SELECT * from admin_menus WHERE id IN(\n" +
            "\tSELECT menuId FROM admin_user_authority WHERE userId=\n" +
            "\t(\n" +
            "\t\t\tSELECT id AS userId FROM admin_users WHERE username=#{param1}\n" +
            "\t)\n" +
            ") AND parentId=#{param2}")
    List<Map<String,Object>> selectMenus(String username, String id);
}
