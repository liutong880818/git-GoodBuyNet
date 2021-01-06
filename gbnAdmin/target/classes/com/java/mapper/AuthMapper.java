package com.java.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description: 描述
 * Date: 2020/11/12 11:57
 * author: LT
 */
@Repository
public interface AuthMapper {

    /**
     * 获取所有后台管理平台的用户列表
     * @return
     */
    @Select("SELECT * FROM admin_users LIMIT #{startIndex},#{pageSize}")
    List<Map<String,Object>> selectUsers(@Param("startIndex") int startIndex,
                                         @Param("pageSize")int pageSize);

    /**
     * 查询用户总记录数
     * @return
     */
    @Select("SELECT COUNT(*) FROM admin_users")
    int selectTotalCount();

    /**
     * 查询admin_user表中指定id记录对应的flag状态
     * @return
     */
    @Select("SELECT flag FROM admin_users WHERE id=#{param1}")
    String selectUserFlagById(String id);

    /**
     * 修改admin_user表中指定id记录对应的flag值
     * @param flag
     * @param id
     * @return
     */
    @Update("UPDATE admin_users SET flag=#{param1} WHERE id=#{param2}")
    int updateUserFlagById(String flag, String id);

    /**
     * 判断指定的用户名在数据库中是否存在
     * @param username
     * @return
     */
    @Select("SELECT COUNT(*) FROM admin_users WHERE username=#{param1}")
    int selectUsernameIsExist(String username);

    /**
     * 插入一条后台用户数据
     * @param paramMap
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    @Insert("INSERT INTO admin_users SET username=#{username},pwd=#{pwd}," +
            "isRoot='0',updateTime=NOW(),flag='0';")
    int insertUser(Map<String,Object> paramMap);

    /**
     * 插入用户与权限的关联关系
     * @return
     */
    int insertUserAuthRelation(@Param("userId") String id,
                               @Param("menuIdSet") Set<String> menuIdSet);

    /**
     * 获取指定id对应的username信息
     * @param userId
     * @return
     */
    @Select("SELECT username FROM admin_users WHERE id=#{param1}")
    String selectUsernameById(String userId);

    /**
     * 获取指定id对应的所有权限信息
     * @param userId
     * @return
     */
    @Select("SELECT menuId FROM admin_user_authority WHERE userId=#{param1}")
    List<String> selectAuthById(String userId);

    /**
     * 删除指定id用户拥有的所有权限
     * @param userId
     * @return
     */
    @Delete("DELETE FROM admin_user_authority WHERE userId=#{param1}")
    int deleteUserAutnById(String userId);
}
