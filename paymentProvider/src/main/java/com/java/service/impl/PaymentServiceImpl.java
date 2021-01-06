package com.java.service.impl;

import com.java.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/29 13:18
 * author: LT
 */
@Service
public class PaymentServiceImpl implements com.java.service.PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    /**
     * 修改支付订单的状态信息
     * @param orderStatus
     * @param userId
     * @param seckillId
     * @return
     */
    @Override
    public Map<String,Object> modifyPaymentOrderStatus(String orderStatus, String userId, String seckillId){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//修改成功
        //1、数据校验
        //2、判断userId和seckillId在web_order表中是否存在

        int flag = paymentMapper.updatePaymentOrderStatus(orderStatus, userId, seckillId);
        if(flag!=1){
            resultMap.put("status","1");
            resultMap.put("msg","支付小哥正在忙，请稍后");
            return resultMap;
        }
        return resultMap;
    }

}
