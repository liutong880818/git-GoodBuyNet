package com.java.controller;

import com.java.service.SeckillPaymentService;
import com.java.utils.OrderNumGenerateUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/29 12:07
 * author: LT
 */
@RestController
@RequestMapping("/seckillPayment")
public class SeckillPaymentController {

    @Autowired
    private SeckillPaymentService seckillPaymentService;

    /**
     * 从RabbitMQ对应的queue-seckill队列中取出秒杀名额，生成对应的订单编号
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-seckill"),
            exchange = @Exchange(value = "ex-seckill",type = "fanout")
    ))
    public void handMQData(@Payload Map<String,Object> dataMap, Channel channel,@Headers Map<String,Object> headers) {
        try {
            //1、从RabbitMQ中获取userId、seckillId
            String userId = dataMap.get("userId")+"";
            String seckillId = dataMap.get("seckillId") + "";
            //2、做安全检测
            System.out.println("seckillPaymentController...............经过安全检测，支付环境很安全");
            //3、生成订单编号
            String orderNo = OrderNumGenerateUtil.generateOrderNum(userId);
            //4、将订单编号、userId、seckill等信息存入数据库
            Map<String, Object> resultMap = seckillPaymentService.saveSeckillPaymentOrder(orderNo, userId, seckillId);
            if(resultMap.get("status")=="0"){ //创建订单成功
                //5、支付宝、微信等进行支付
                System.out.println("支付中................");
                //6、手动确认订单处理完毕
                channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG),false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
