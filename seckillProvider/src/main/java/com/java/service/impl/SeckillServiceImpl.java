package com.java.service.impl;

import com.java.mapper.SeckillMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/26 15:01
 * author: LT
 */
@Service
public class SeckillServiceImpl implements com.java.service.SeckillService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 查询 正在秒杀的商品 的状态
     * @param seckillId
     * @return
     */
    @Override
    public Map<String,Object> modifySeckill(String userId, String seckillId){
        //数据校验

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0"); //成功秒杀，抢到一个秒杀名额
        //1、查询秒杀商品的状态
        //优化并发量前：从mysql中取
        //String productStatus = seckillMapper.selectSeckillProductStatus(seckillId);
        //优化并发量后：从redis中取
        ValueOperations vo = redisTemplate.opsForValue();
        String productStatus = vo.get("seckill_product_status_" + seckillId)+"";
        if(productStatus==null){
            productStatus="2";
        }
        //2、判断秒杀商品的状态
        if("0".equals(productStatus)){// 状态为0时，秒杀还未开始
            resultMap.put("status","1");
            resultMap.put("msg","本场秒杀还未开始，稍后请关注");
            return  resultMap;
        }
        if("2".equals(productStatus)){// 状态为2时，秒杀已经结束
            resultMap.put("status","1");
            resultMap.put("msg","本场秒杀已经结束，请关注下一场");
            return  resultMap;
        }
        //3、删除redis的List集合中的一个商品ID
        ListOperations loList = redisTemplate.opsForList();
        Object loValue = loList.leftPop("seckill_product_" + seckillId);
        //4、判断 loValue 是否为null
        if(loValue==null){ //正在秒杀的商品已经被抢完了
            resultMap.put("status","1");
            resultMap.put("msg","本场秒杀已抢完，已无秒杀名额，请关注下一场");
            return  resultMap;
        }
        //4、判断用户是否重复抢购（判断keyt = seckill_user_seckillId 的set集合中，是否已经存在某个userId）
        SetOperations soSet = redisTemplate.opsForSet();
        Boolean flag = soSet.isMember("seckill_user_" + seckillId, userId);
        if(flag){ //用户之前已经抢到了一个秒杀名额，即userId已存在redis的set集合中
            loList.leftPush("seckill_product_" + seckillId, seckillId);
            resultMap.put("status","1");
            resultMap.put("msg","每人只有一个秒杀名额，不能重复抢购");
            return  resultMap;
        }
        //5、秒杀成功，往redis对应的Set集合中添加 成功秒杀的userId
        soSet.add("seckill_user_" + seckillId, userId);
        //6、对接RabbitMQ
        return resultMap;
    }

    /**
     * 往RabbitMQ中存放数据
     * @param userId
     * @param seckillId
     */
    @Override
    public void saveDataToRabbitMQ(String userId, String seckillId) {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("userId",userId);
        dataMap.put("seckillId",seckillId);
        rabbitTemplate.convertAndSend("ex-seckill",null,dataMap);
    }

}
