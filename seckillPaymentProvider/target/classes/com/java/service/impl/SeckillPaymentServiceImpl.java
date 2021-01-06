package com.java.service.impl;

import com.java.mapper.SeckillPaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/29 12:40
 * author: LT
 */
@Service
public class SeckillPaymentServiceImpl implements com.java.service.SeckillPaymentService {

    @Autowired
    private SeckillPaymentMapper seckillPaymentMapper;

    /**
     * 添加秒杀支付的订单
     * @param orderNo
     * @param userId
     * @param seckillId
     * @return
     */
    @Override
    public Map<String,Object> saveSeckillPaymentOrder(String orderNo, String userId, String seckillId){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//添加成功
        //1、数据校验
        //2、判断数据库对应的user表中是否存在userId这个用户（此user表目前不存在）

        int flag = seckillPaymentMapper.insertSeckillPaymentOrder(orderNo, userId, seckillId);
        if (flag != 1) {
            resultMap.put("status","1");
            resultMap.put("msg","添加订单失败");
            return resultMap;
        }
        return resultMap;
    }
}
