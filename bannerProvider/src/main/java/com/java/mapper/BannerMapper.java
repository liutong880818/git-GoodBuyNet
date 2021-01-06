package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/22 23:27
 * author: LT
 */
@Repository
public interface BannerMapper {

    /**
     * 获取最新8张轮播图片
     * @return
     */
    @Select("SELECT * FROM web_banner ORDER BY updateTime DESC LIMIT 8")
    List<Map<String,Object>> selectBanner();

}
