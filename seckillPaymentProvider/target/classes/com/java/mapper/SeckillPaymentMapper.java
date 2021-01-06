package com.java.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * Description: 描述
 * Date: 2020/12/29 12:36
 * author: LT
 */
@Repository
public interface SeckillPaymentMapper {

    /**
     * 插入秒杀支付的订单信息
     * @param orderNo
     * @param userId
     * @param seckillId
     * @return
     */
    @Insert("INSERT INTO web_order SET orderNo=#{param1},userId=#{param2},orderStatus='0',seckillId=#{param3},\n" +
            "createOrderDate=NOW(),createOrderPaymentDate=NOW()")
    int insertSeckillPaymentOrder(String orderNo,String userId,String seckillId);

}
