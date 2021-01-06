package com.java.service.impl;

import com.java.mapper.TransverseNavMapper;
import com.java.service.TransverseNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description: 描述
 * Date: 2020/11/10 13:08
 * author: LT
 */
@Service
public class TransverseNavImpl implements TransverseNavService {

    @Autowired
    private TransverseNavMapper transverseNavMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取8个横向导航栏信息
     * @return
     */
    @Override
    public List<Map<String, Object>> findTransverseNavs() {
        try {
            //1.从redisTemplate中获取value的工具类实例（value是通用的）
            ValueOperations vo = redisTemplate.opsForValue();
            //2.通过key将数据从redis中取出
            Object transverseNavValue = vo.get("transverseNavListKey");
            //3.判断redis中是否存在此key的数据
            if(transverseNavValue==null){//redis中没有此key的数据
                //4.从mysql对应的表中查询数据
                List<Map<String, Object>> transverseNavList = transverseNavMapper.selectTransverseNavs();
                //5.将数据存到redis中，并且指定失效时间
                vo.set("transverseNavListKey",transverseNavList,1, TimeUnit.MINUTES);
                return transverseNavList;
            }else {//redis中存在此key的数据
                return (List<Map<String, Object>>) transverseNavValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return transverseNavMapper.selectTransverseNavs();
        }
    }
}
