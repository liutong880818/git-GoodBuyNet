package com.java.service;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/29 13:00
 * author: LT
 */
public interface SeckillPaymentService {

    /**
     * 添加秒杀支付的订单
     * @param orderNo
     * @param userId
     * @param seckillId
     * @return
     */
    Map<String,Object> saveSeckillPaymentOrder(String orderNo, String userId, String seckillId);
}
