package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/19 23:03
 * author: LT
 */
@RestController
@RequestMapping("/discuss")
public class DiscussController {

    //Ribbon负载均衡对象：RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取评论信息
     * @param productNum
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getDiscuss")
    public Map<String,Object> getDiscuss(String productNum,
                                         @RequestParam(defaultValue = "1") int pageNum,
                                         @RequestParam(defaultValue = "5") int pageSize){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("productNum",productNum);
        paramMap.put("pageNum",pageNum);
        paramMap.put("pageSize",pageSize);
        //方式1：
        //return restTemplate.getForObject("http://discussProvider/discuss/getDiscuss?productNum="+productNum+"&pageNum="+pageNum+"&pageSize="+pageSize, Map.class, paramMap);
        //方式2：
        return restTemplate.getForObject("http://discussProvider/discuss/getDiscuss?productNum={productNum}&pageNum={pageNum}&pageSize={pageSize}",Map.class,paramMap);
        //方式3：
        //return restTemplate.getForObject("http://discussProvider/discuss/getDiscuss?productNum={productNum}&pageNum={pageNum}&pageSize={pageSize}",Map.class,productNum,pageNum,pageSize);
    }

    /**
     * 添加评论信息
     * @param paramMap
     * @return
     */
    @RequestMapping("/addDiscuss")
    public Map<String,Object> addDiscuss(@RequestParam Map<String,Object> paramMap){
        String paramUrl = "productNum={productNum}&content={content}&color={color}&type={type}&headIconUrl={headIconUrl}&flag={flag}&userId={userId}";
        return restTemplate.getForObject("http://discussProvider/discuss/addDiscuss?"+paramUrl,Map.class,paramMap);
        //return restTemplate.postForObject("http://discussProvider/discuss/addDiscuss?"+paramUrl,paramMap,Map.class);
    }

}
