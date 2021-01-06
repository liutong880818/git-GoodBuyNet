package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Description: 描述
 * Date: 2020/12/26 14:57
 * author: LT
 */
@Repository
public interface SeckillMapper {

    /**
     * 获取 正在秒杀的商品 的状态
     * @param seckillId
     * @return
     */
    @Select("SELECT `status` FROM web_seckill WHERE id=#{param1}")
    String selectSeckillProductStatus(String seckillId);

}
