package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/24 13:42
 * author: LT
 */
@Controller
@RequestMapping("/FreeMarker")
public class FreeMarkerController {

    //controller方法-->request-->fmDemo1.ftl
    @RequestMapping("/toFmDemo")
    public ModelAndView toFmDemo(){
        //1、从后台数据库获取某个商品的详细信息
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("title","联想电脑");
        resultMap.put("subtitle","cpu i10");
        resultMap.put("price",5999.9F);
        resultMap.put("type","旗舰版");
        resultMap.put("color","黑色");
        resultMap.put("flag1",true);
        resultMap.put("flag2",false);
        List<String> imgUrlList = Arrays.asList("http://192.168.25.133/group1/M00/00/04/wKgZhV_jXpKAaFTWAABD_zdjoXY868.jpg",
                "http://192.168.25.133/group1/M00/00/04/wKgZhV_jXsiAYEF4AAA_FrDP7dQ276.jpg",
                "http://192.168.25.133/group1/M00/00/04/wKgZhV_jXuKARGrsAAAsArZ94fE426.jpg");
        resultMap.put("imgUrlList",imgUrlList);
        //2、转发到fmDemo1.ftl
        ModelAndView mav = new ModelAndView("fmDemo1");

        //等价于1)model.addAttribute(mav) 2)return "forward:/templates/fmDemo1.ftl"
        mav.addAllObjects(resultMap);
        return mav;
    }

}
