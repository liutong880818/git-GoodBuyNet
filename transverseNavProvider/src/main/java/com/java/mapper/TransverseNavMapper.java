package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/11/10 13:05
 * author: LT
 */
@Repository
public interface TransverseNavMapper {

    /**
     * 获取8个横向导航栏信息
     * @return
     */
    @Select("SELECT * FROM web_menu WHERE menuType=1 LIMIT 8;")
    List<Map<String,Object>> selectTransverseNavs();

}
