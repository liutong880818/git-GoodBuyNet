package com.java.service.impl;

import com.java.mapper.SeckillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/26 12:08
 * author: LT
 */
@Service
public class SeckillServiceImpl implements com.java.service.SeckillService {

    @Autowired
    private SeckillMapper seckillMapper;

    /**
     * 秒杀商品开始前，查询即将参与秒杀的商品
     * @return
     */
    @Override
    public List<Map<String,Object>> selectSeckillProductBefore(){
        return seckillMapper.selectSeckillProductBefore();
    }

    /**
     * 秒杀商品正在秒杀时，将 即将参与秒杀的商品 的状态，由“未秒杀”状态改为“正在秒杀”状态
     * @return
     */
    @Override
    public void modifySeckillProductStatusIng(){
        seckillMapper.updateSeckillProductStatusIng();
    }

    /**
     * 秒杀商品结束后，将 正在秒杀的商品 的状态，由“正在秒杀”状态改为“秒杀结束”状态
     * @return
     */
    @Override
    public void modifySeckillProductStatusOver(){
        seckillMapper.updateSeckillProductStatusOver();
    }

    /**
     * 查询 由未开始秒杀 到进入秒杀 的所有商品信息
     * @return
     */
    @Override
    public List<Map<String, Object>> findNotstartToIngProduct() {
        return seckillMapper.selectNotstartToIngProduct();
    }

    /**
     * 查询 由正在进行秒杀 到秒杀结束 的所有商品信息
     * @return
     */
    @Override
    public List<Map<String, Object>> findIngToOverProduct() {
        return seckillMapper.selectIngToOverProduct();
    }

}
