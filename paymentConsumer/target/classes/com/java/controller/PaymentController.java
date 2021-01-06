package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/29 13:54
 * author: LT
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 修改支付订单的状态信息  --- 消费者
     * @param flag
     * @param userId
     * @param seckillId
     * @return
     */
    @RequestMapping("/updatePaymentOrderStatus")
    public Map<String,Object> updatePaymentOrderStatus(String flag,String userId,String seckillId){
        return restTemplate.getForObject("http://paymentProvider/payment/updatePaymentOrderStatus?flag={flag}&userId={userId}&seckillId={seckillId}",
                Map.class,flag,userId,seckillId);
    }

}
