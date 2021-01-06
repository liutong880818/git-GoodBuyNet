package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/28 14:52
 * author: LT
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 秒杀抢购接口--消费者
     * @param userId
     * @param seckillId
     * @return
     */
    @RequestMapping("/updateSeckill")
    public Map<String,Object> updateSeckill(String userId, String seckillId){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",userId);
        paramMap.put("seckillId",seckillId);
        return restTemplate.getForObject("http://seckillProvider/seckill/updateSeckill?userId={userId}&seckillId={seckillId}",Map.class,paramMap);
    }
}
