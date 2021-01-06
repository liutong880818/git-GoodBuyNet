package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/25 0:24
 * author: LT
 */
@Repository
public interface ProductMapper {

    /**
     * 获取所有商品的详细信息
     * @return
     */
    @Select("SELECT * FROM web_product_detail wpd INNER JOIN\n" +
            "web_product_img wpi ON wpd.id=wpi.productId GROUP BY wpd.id")
    List<Map<String,Object>> selectProductDetailInfo();

    /**
     * 根据商品id获取此商品的所有图片
     * @param productId
     * @return
     */
    @Select("SELECT imgUrl FROM web_product_img WHERE productId=#{param1}")
    List<String> selectProductImgByProductId(String productId);

}
