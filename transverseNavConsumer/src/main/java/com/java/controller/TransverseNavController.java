package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/11/10 16:37
 * author: LT
 */
@Controller
@RequestMapping("/transverseNav")
public class TransverseNavController {

    //Ribbon负载均衡对象：RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 横向导航栏消费者方法
     * @return
     */
    @RequestMapping("/getTransverseNavs")
    public @ResponseBody List<Map<String,Object>> getTransverseNavList(){
        //在springCloud当中，服务与服务之间相互调用有两种方式：
        //1. restTemplate调用 2. feign
        List<Map<String,Object>> transverseNavList = restTemplate.getForObject("http://transverseNavProvider/transverseNav/getTransverseNavs", List.class);
        return transverseNavList;
    }

}
