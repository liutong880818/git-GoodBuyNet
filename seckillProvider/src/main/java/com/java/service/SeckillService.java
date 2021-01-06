package com.java.service;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/26 15:05
 * author: LT
 */
public interface SeckillService {

    /**
     * 查询 正在秒杀的商品 的状态
     * @param seckillId
     * @return
     */
    Map<String,Object> modifySeckill(String userId,String seckillId);

    /**
     * 往RabbitMQ中存放数据
     * @param userId
     * @param seckillId
     */
    void saveDataToRabbitMQ(String userId, String seckillId);
}
