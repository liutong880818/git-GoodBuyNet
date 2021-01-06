package com.java.controller;

import com.java.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 权限模块
 * Date: 2020/11/12 12:09
 * author: LT
 */

//@Controller
@RestController  //相当于在控制层的类上添加@Controller，同时在类中的每个方法上面添加@ResponseBody
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 获取所有后台管理平台的用户列表
     * @return
     */
    @RequestMapping("/getUsers")
    public /*@ResponseBody*/ Map<String,Object> getUsers(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int rows){
        return authService.findUsers(page, rows);
    }

    /**
     * 修改用户状态
     * @param id
     * @return
     */
    @RequestMapping("/updateUserFlag")
    public Map<String,Object> updateUserFlag(String id){
        return authService.modifyUserFlagById(id);
    }

    /**
     * 创建用户并且授权
     * @param username
     * @param menuIdStr
     * @return
     */
    @RequestMapping("/addUserAuth")
    public Map<String,Object> addUserAuth(String username,String menuIdStr){
        return authService.saveUserAuth(username,menuIdStr);
    }

    /**
     * 根据用户id获取用户名和用户权限信息
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfoById")
    public Map<String,Object> getUserInfoById(String userId){
        return authService.findUserInfo(userId);
    }

    /**
     * 重新给用户授权
     * @param userId
     * @param menuIdStr
     * @return
     */
    @RequestMapping("/addUserAutnAgain")
    public Map<String,Object> addUserAutnAgain(String userId,String menuIdStr){
        return authService.saveUserAuthAgain(userId, menuIdStr);
    }
}
