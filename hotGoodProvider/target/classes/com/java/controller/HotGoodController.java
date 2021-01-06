package com.java.controller;

import com.java.service.HotGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/23 20:13
 * author: LT
 */
@RestController
@RequestMapping("/hotGood")
public class HotGoodController {

    @Autowired
    private HotGoodService hotGoodService;

    @RequestMapping("/getHotGood")
    public Map<String,Object> getHotGoodList(){
        return hotGoodService.findHotGood();
    }

}
