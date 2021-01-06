package com.java.controller;

import com.java.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/26 15:09
 * author: LT
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 秒杀抢购接口--提供者
     * @param seckillId
     * @return
     */
    @RequestMapping("/updateSeckill")
    public Map<String,Object> updateSeckill(String userId, String seckillId){
        Map<String, Object> resultMap = seckillService.modifySeckill(userId, seckillId);
        if(resultMap.get("status")=="0"){//秒杀成功，再去存入rabbitMQ，最后进行支付结算
            seckillService.saveDataToRabbitMQ(userId,seckillId);
        }
        return resultMap;
    }
}
