package com.java.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.java.mapper.AuthMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Description: 描述
 * Date: 2020/11/12 12:01
 * author: LT
 */
@Service
@Transactional(readOnly = false)
public class AuthServiceImpl implements com.java.service.AuthService {

    @Autowired
    private AuthMapper authMapper;

    /**
     * 获取所有后台管理平台的用户列表
     * @return
     */
    @Override
    public Map<String,Object> findUsers(int page,int rows){
        Map<String,Object> resultMap = new HashMap<>();
        int totalCount = authMapper.selectTotalCount();
        List<Map<String, Object>> userList = authMapper.selectUsers((page - 1) * rows, rows);
        resultMap.put("total",totalCount);
        resultMap.put("rows",userList);
        return resultMap;
    }

    @Override
    public Map<String, Object> modifyUserFlagById(String id) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");
        //1、执行当前操作的用户是否是超级管理员
        //2、如果执行操作的是超级管理员，则获取被操作用户的flag状态
        if(id==null||!(id.matches("[1-9]\\d*"))){
            resultMap.put("status","1");
            resultMap.put("msg","数据格式错误");
            return resultMap;
        }
        String flag = authMapper.selectUserFlagById(id);
        if(flag==null){
            resultMap.put("status","1");
            resultMap.put("msg","被操作的用户不存在");
            return resultMap;
        }
        //如果用户原来flag 状态为1，则改成0，为0，则改成1
        flag = ("0".equals(flag))?"1":"0";
        //3、修改id用户的flag状态
        int flag1 = authMapper.updateUserFlagById(flag, id);
        if(flag1!=1){
            resultMap.put("status","1");
            resultMap.put("msg","修改失败");
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> saveUserAuth(String username, String menuIdStr) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");
        try {
            //1、数据校验
            if(username==null||menuIdStr==null){
                resultMap.put("status","1");
                resultMap.put("msg","数据不能为空");
                return resultMap;
            }
            if(!username.matches("\\w{3,12}")||!menuIdStr.matches("([1-9]\\d*,)*")){
                resultMap.put("status","1");
                resultMap.put("msg","数据格式错误");
                return resultMap;
            }
            //2、查看用户名是否存在
            int flag = authMapper.selectUsernameIsExist(username);
            if(flag==1){
                resultMap.put("status","1");
                resultMap.put("msg","此用户名已经存在，请重新输入");
                return resultMap;
            }
            //3、向admin_users表中插入记录
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("username",username);
            paramMap.put("pwd", SecureUtil.md5("123456"));
            authMapper.insertUser(paramMap);
            //4、判断前台用户是否勾选了至少一个权限
            if (!"".equals(menuIdStr)) {
                //5、插入userId与权限的映射关系
                //解析menuIdStr字符串: 权限id可能会重复
                String[] menuIdArr = menuIdStr.split(",");
                Set<String> menuIdSet = new HashSet<>(Arrays.asList(menuIdArr));
                String userId = paramMap.get("userId")+"";
                authMapper.insertUserAuthRelation(userId,menuIdSet);
            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","1");
            resultMap.put("msg","后台小哥出差了");
            return resultMap;
        }
    }

    @Override
    public Map<String, Object> findUserInfo(String userId) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");
        //1、数据校验
        if(userId==null||!userId.matches("[1-9]\\d*")){
            resultMap.put("status","1");
            resultMap.put("msg","数据格式错误");
            return resultMap;
        }
        //2、根据userId查询username
        String username = authMapper.selectUsernameById(userId);
        //3、根据userId查询用户拥有的所有权限信息
        List<String> menuIdList = authMapper.selectAuthById(userId);
        resultMap.put("username",username);
        resultMap.put("menuIdList",menuIdList);
        return resultMap;
    }

    @Override
    public Map<String, Object> saveUserAuthAgain(String userId, String menuIdStr) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");
        //1、数据校验
        if(userId==null||menuIdStr==null){
            resultMap.put("status","1");
            resultMap.put("msg","数据不能为空");
            return resultMap;
        }
        if(!userId.matches("[1-9]\\d*")||!menuIdStr.matches("([1-9]\\d*,)*")){
            resultMap.put("status","1");
            resultMap.put("msg","数据格式错误");
            return resultMap;
        }
        //2、删除某个用户拥有的所有权限信息
        authMapper.deleteUserAutnById(userId);
        //3、重新给用户授权
        System.out.println(menuIdStr);
        System.out.println("".equals(menuIdStr));
        if(!"".equals(menuIdStr)){
            String[] menuIdArr = menuIdStr.split(",");
            Set<String> menuIdSet = new HashSet<>(Arrays.asList(menuIdArr));
            authMapper.insertUserAuthRelation(userId,menuIdSet);
        }
        return resultMap;
    }
}
