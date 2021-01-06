package com.java.controller;

import com.java.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Description: 导航栏
 * Date: 2020/11/4 14:48
 * author: LT
 */
@Controller
@RequestMapping("/nav")
public class NavController {

    @Autowired
    private NavService navService;

    /**
     * 获取前台横向导航栏信息
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getTransverseNavs")
    public @ResponseBody Map<String,Object> getTransverseNavs(@RequestParam(defaultValue = "1") int page,
                                                              @RequestParam(defaultValue = "10") int rows){
        return navService.findNavs(page,rows);
    }

    /**
     * 添加一条横向导航栏信息
     * @param title
     * @param url
     * @return
     */
    @RequestMapping("/addTransverseNav")
    public @ResponseBody Map<String,Object> addTransverseNav(String title,String url){
        return navService.saveNav(title,url);
    }

    /**
     * 根据id查询某个指定横向导航栏信息
     * @param id
     * @return
     */
    @RequestMapping("/getTransverseNavById")
    public @ResponseBody Map<String,Object> getTransverseNavById(String id){
        return navService.findTransverseNavById(id);
    }

    /**
     * 根据id修改某个指定横向导航栏信息
     * @param title
     * @param url
     * @param id
     * @return
     */
    @RequestMapping("/updateTransverseNavById")
    public @ResponseBody Map<String,Object> updateTransverseNavById(String title,String url,String id){
        return navService.modifyTransverseNavById(title, url, id);
    }

    /**
     * 根据id删除某个指定横向导航栏信息
     * @param idStr
     * @return
     */
    @RequestMapping("/deleteTransverseNavById")
    public @ResponseBody Map<String,Object> deleteTransverseNavById(String idStr){
        return navService.removeTransverseNavById(idStr);
    }
}
