package com.java.service;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/25 0:33
 * author: LT
 */
public interface ProductService {

    /**
     * 获取所有商品的详细信息
     * @return
     */
    List<Map<String,Object>> findProductDetailInfo();

    /**
     * 根据商品id获取此商品的所有图片
     * @param productId
     * @return
     */
    List<String> findProductImgByProductId(String productId);
}
