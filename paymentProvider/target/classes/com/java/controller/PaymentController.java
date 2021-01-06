package com.java.controller;

import com.java.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/29 13:10
 * author: LT
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 修改支付订单的状态信息
     * @param flag
     * @param userId
     * @param seckillId
     * @return
     */
    @RequestMapping("/updatePaymentOrderStatus")
    public Map<String,Object> updatePaymentOrderStatus(String flag,String userId,String seckillId){
        //判断用户在前端是选择，确认支付，还是，取消支付
        String orderStatus = "";
        if("cancel".equals(flag)){ //取消支付  未支付
            orderStatus = "1";
        }
        if("ok".equals(flag)){ //确认支付  已支付
            //对接支付宝、微信等
            //如果支付宝、微信等，支付成功，再把status="2"
            orderStatus = "2";
        }
        return paymentService.modifyPaymentOrderStatus(orderStatus,userId,seckillId);
    }

}
