package com.java.service.impl;

import com.java.mapper.HotGoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description: 描述
 * Date: 2020/12/23 18:26
 * author: LT
 */
@Service
public class HotGoodServiceImpl implements com.java.service.HotGoodService {

    @Autowired
    private HotGoodMapper hotGoodMapper;

    @Autowired
    private RedisTemplate  redisTemplate;

    /**
     * 查询热门商品列表
     * @return
     */
    @Override
    public Map<String,Object> findHotGood(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");
        try {
            ValueOperations vo = redisTemplate.opsForValue();
            Object hotGoodListValue = vo.get("hotGoodList");
            if(hotGoodListValue==null){
                List<Map<String, Object>> hotGoodList = hotGoodMapper.selectHotGoodList();
                vo.set("hotGoodList",hotGoodList,5, TimeUnit.MINUTES);
                resultMap.put("hotGoodList",hotGoodList);
                return resultMap;
            }
            else {
                resultMap.put("hotGoodList",hotGoodListValue);
                return resultMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","1");
            resultMap.put("msg","热门商品列表获取失败");
            return resultMap;
        }
    }
}
