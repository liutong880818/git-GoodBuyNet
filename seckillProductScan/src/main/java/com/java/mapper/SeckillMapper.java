package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/26 11:49
 * author: LT
 */
@Repository
public interface SeckillMapper {

    /**
     * 秒杀商品开始前，查询即将参与秒杀的商品
     * @return
     */
    @Select("SELECT * FROM web_seckill WHERE `status`='0' AND startTime>=NOW()")
    List<Map<String,Object>> selectSeckillProductBefore();

    /**
     * 秒杀商品正在秒杀时，将 即将参与秒杀的商品 的状态，由“未秒杀”状态改为“正在秒杀”状态
     * @return
     */
    @Update("UPDATE web_seckill SET `status`='1' WHERE `status`='0' AND startTime<=NOW() AND endTime>=NOW()")
    int updateSeckillProductStatusIng();

    /**
     * 秒杀商品结束后，将 正在秒杀的商品 的状态，由“正在秒杀”状态改为“秒杀结束”状态
     * @return
     */
    @Update("UPDATE web_seckill SET `status`='2' WHERE `status`='1' AND endTime<NOW()")
    int updateSeckillProductStatusOver();

    /**
     * 查询 由未开始秒杀 到进入秒杀 的所有商品信息
     * @return
     */
    @Select("SELECT * FROM web_seckill WHERE `status`='0' AND startTime<=NOW() AND endTime>=NOW()")
    List<Map<String,Object>> selectNotstartToIngProduct();

    /**
     * 查询 由正在进行秒杀 到秒杀结束 的所有商品信息
     * @return
     */
    @Select("SELECT * FROM web_seckill WHERE `status`='1' AND endTime<NOW()")
    List<Map<String,Object>> selectIngToOverProduct();
}
