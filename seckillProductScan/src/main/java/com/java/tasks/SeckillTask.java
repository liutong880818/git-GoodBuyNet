package com.java.tasks;

import com.java.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description: 描述
 * Date: 2020/12/26 12:17
 * author: LT
 */
@Component
public class SeckillTask {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 秒杀商品开始前，查询即将参与秒杀的商品，获取商品id和商品秒杀数量，将相关信息存放到redis中
     * @return
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void getSeckillProductBefore(){
        System.out.println("秒杀商品开始前，查询即将参与秒杀的商品，获取商品id和商品秒杀数量，将相关信息存放到redis中");
        //1、获取即将参与秒杀的商品信息
        List<Map<String, Object>> seckillProductBeforeList = seckillService.selectSeckillProductBefore();
        //2、循环取出秒杀商品的主键Id，秒杀数量，存放到redis
        ListOperations lo = redisTemplate.opsForList();
        ValueOperations vo = redisTemplate.opsForValue();
        for(int i=0; seckillProductBeforeList!=null && i<seckillProductBeforeList.size(); i++){
            String seckillId = seckillProductBeforeList.get(i).get("id")+"";
            int num = Integer.parseInt(seckillProductBeforeList.get(i).get("num") + "");
            //构建秒杀商品在redis中的key，同一商品可能每天都参与秒杀，取商品id有可能重复出现
            //所以取seckillId，数据库中唯一
            String key = "seckill_product_"+seckillId;
            //3、判断 即将参与秒杀的商品 是否已经存到redis中准备进行秒杀
            Boolean flag = redisTemplate.hasKey(key);
            if(!flag){ //如果redis中不存在此key
                //存放 即将参与秒杀的商品 的状态信息
                vo.set("seckill_product_status_" + seckillId,"0");
                //4、往redis中存放数据
                for(int j=1; j<=num; j++){
                    lo.leftPush(key,seckillId);
                }
            }
        }
    }

    /**
     * 秒杀商品正在秒杀时，将 即将参与秒杀的商品 的状态，由“未秒杀”状态改为“正在秒杀”状态
     * @return
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void updateSeckillProductStatusIng(){
        System.out.println("秒杀商品正在秒杀时，将 即将参与秒杀的商品 的状态，由“未秒杀”状态改为“正在秒杀”状态");
        //redis中，商品的状态也需由0改为1
        ValueOperations vo = redisTemplate.opsForValue();
        List<Map<String, Object>> productList = seckillService.findNotstartToIngProduct();
        for(int i=0; productList!=null && i<productList.size(); i++){
            String seckillId = productList.get(i).get("id")+"";
            //修改redis缓存中的商品状态信息
            vo.set("seckill_product_status_"+seckillId, "1");
        }
        //将mysql数据库中，秒杀表中，商品的状态由0改为1
        seckillService.modifySeckillProductStatusIng();
    }

    /**
     * 秒杀商品结束后，将 正在秒杀的商品 的状态，由“正在秒杀”状态改为“秒杀结束”状态
     * @return
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void updateSeckillProductStatusOver(){
        System.out.println("秒杀商品结束后，将 正在秒杀的商品 的状态，由“正在秒杀”状态改为“秒杀结束”状态");
        //redis中，商品的状态也需由1改为2
        ValueOperations vo = redisTemplate.opsForValue();
        List<Map<String, Object>> productList = seckillService.findIngToOverProduct();
        for(int i=0; productList!=null && i<productList.size(); i++){
            String seckillId = productList.get(i).get("id")+"";
            //修改redis缓存中的商品状态信息
            vo.set("seckill_product_status_"+seckillId, "2",3, TimeUnit.MINUTES);
            //下架redis中的秒杀记录
            redisTemplate.delete("seckill_product_" + seckillId);
        }
        //将mysql数据库中，秒杀表中，商品的状态由1改为2
        seckillService.modifySeckillProductStatusOver();
    }

}
