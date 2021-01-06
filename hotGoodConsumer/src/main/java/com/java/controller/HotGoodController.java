package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/23 20:19
 * author: LT
 */
@RestController
@RequestMapping("/hotGood")
public class HotGoodController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取热门商品列表
     * @return
     */
    @RequestMapping("/getHotGood")
    public Map<String,Object> getHotGood(){
        return restTemplate.getForObject("http://hotGoodProvider/hotGood/getHotGood",Map.class);
    }

}
