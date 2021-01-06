package com.java.service;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/29 13:34
 * author: LT
 */
public interface PaymentService {
    /**
     * 修改支付订单的状态信息
     * @param orderStatus
     * @param userId
     * @param seckillId
     * @return
     */
    Map<String,Object> modifyPaymentOrderStatus(String orderStatus, String userId, String seckillId);
}
