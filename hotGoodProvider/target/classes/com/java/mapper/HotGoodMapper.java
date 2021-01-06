package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/23 18:22
 * author: LT
 */
@Repository
public interface HotGoodMapper {

    /**
     * 查询热门商品列表
     * @return
     */
    @Select("SELECT wpd.title,wpd.subTitle,wpd.price,wpd.href,wpi.imgUrl\n" +
            "FROM web_product_detail wpd INNER JOIN web_product_img wpi\n" +
            "ON wpd.id=wpi.productId GROUP BY wpd.id")
    List<Map<String,Object>> selectHotGoodList();

}
