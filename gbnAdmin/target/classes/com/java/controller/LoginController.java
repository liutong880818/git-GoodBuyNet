package com.java.controller;

import com.java.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Description: 登录
 * Date: 2020/10/28 14:38
 * author: LT
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/getLogin")
    public @ResponseBody Map<String,Object> getLogin(String username, String password, HttpSession session){
        return loginService.findLogin(username, password);
    }

    /**
     * 查询登录的用户对应拥有的菜单栏信息
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/getMenus")
    public @ResponseBody List<Map<String,Object>> getMenus(@RequestParam(name = "id",defaultValue = "0",required = false)
                                                                       String id, HttpSession session){
        return loginService.findMenus(id);
    }

}
