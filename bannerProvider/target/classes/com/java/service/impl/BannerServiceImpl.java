package com.java.service.impl;

import com.java.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description: 描述
 * Date: 2020/12/22 23:30
 * author: LT
 */
@Service
public class BannerServiceImpl implements com.java.service.BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取最新8张轮播图片
     * @return
     */
    @Override
    public Map<String,Object> findBanner(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//代表获取成功
        try {
            ValueOperations vo = redisTemplate.opsForValue();
            Object bannerListValue = vo.get("bannerListKey");
            //1、判断redis中是否存在此key的数据
            //2、如果redis中不存在此key数据，从mysql中取出，取完后存放到redis缓存中
            if(bannerListValue==null){
                List<Map<String, Object>> bannerList = bannerMapper.selectBanner();
                vo.set("bannerListKey",bannerList,5,TimeUnit.MINUTES);
                resultMap.put("bannerList",bannerList);
                return resultMap;
            }else {
                //3、如果redis中存在此key数据，从redis中取出
                resultMap.put("bannerList",bannerListValue);
                return resultMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","1");
            resultMap.put("msg","轮播图获取失败");
            return resultMap;
        }
    }

}
