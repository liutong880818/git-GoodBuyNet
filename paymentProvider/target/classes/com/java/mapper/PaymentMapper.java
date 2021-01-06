package com.java.mapper;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Description: 描述
 * Date: 2020/12/29 13:15
 * author: LT
 */
@Repository
public interface PaymentMapper {

    /**
     * 修改支付订单的状态信息
     * @param orderStatus
     * @param userId
     * @param seckillId
     * @return
     */
    @Update("UPDATE web_order SET orderStatus=#{param1} WHERE userId=#{param2} AND seckillId=#{param3}")
    int updatePaymentOrderStatus(String orderStatus,String userId,String seckillId);

}
