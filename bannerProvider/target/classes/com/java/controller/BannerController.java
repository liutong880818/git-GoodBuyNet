package com.java.controller;

import com.java.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/22 23:59
 * author: LT
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * 获取轮播图信息
     * @return
     */
    @RequestMapping("/getBanner")
    public Map<String,Object> getBanner(){
        return bannerService.findBanner();
    }

}
