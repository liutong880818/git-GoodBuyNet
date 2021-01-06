package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/23 0:13
 * author: LT
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    //Ribbon负载均衡对象：RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取轮播图信息
     * @return
     */
    @RequestMapping("/getBanner")
    public Map<String,Object> getBanner(){
        return restTemplate.getForObject("http://bannerProvider/banner/getBanner",Map.class);
    }

}
