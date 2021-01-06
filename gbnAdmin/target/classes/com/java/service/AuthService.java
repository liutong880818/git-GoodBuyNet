package com.java.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/11/12 12:08
 * author: LT
 */
public interface AuthService {
    /**
     * 获取所有后台管理平台的用户列表
     * @return
     */
    Map<String,Object> findUsers(int page,int rows);

    /**
     * 修改用户的flag状态
     * @return
     */
    Map<String,Object> modifyUserFlagById(String id);

    /**
     * 添加用户与授权
     * @return
     */
    Map<String,Object> saveUserAuth(String username, String menuIdStr);

    /**
     * 根据id查询对应的用户信息
     * @param userId
     * @return
     */
    Map<String,Object> findUserInfo(String userId);

    /**
     * 编辑并再次授权用户权限
     * @param userId
     * @param menuIdStr
     * @return
     */
    Map<String,Object> saveUserAuthAgain(String userId,String menuIdStr);
}
