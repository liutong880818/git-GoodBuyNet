package com.java.controller;

import com.java.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/17 16:10
 * author: LT
 */
@RestController
@RequestMapping("/discuss")
public class DiscussController {

    @Autowired
    private DiscussService discussService;

    /**
     * 获取评论信息
     * @param productNum
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getDiscuss")
    public Map<String,Object> getDiscuss(@RequestParam String productNum, int pageNum, int pageSize){
        return discussService.findDiscuss(productNum, pageNum, pageSize);
    }

    /**
     * 添加评论信息
     * @param paramMap
     * @return
     */
    @RequestMapping("/addDiscuss")
    public Map<String,Object> addDiscuss(@RequestParam Map<String,Object> paramMap){
        return discussService.saveDiscuss(paramMap);
    }
}
