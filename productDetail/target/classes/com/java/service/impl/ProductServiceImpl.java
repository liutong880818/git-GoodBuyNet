package com.java.service.impl;

import com.java.mapper.ProductMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/25 0:30
 * author: LT
 */
@Service
public class ProductServiceImpl implements com.java.service.ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 获取所有商品的详细信息
     * @return
     */
    @Override
    public List<Map<String,Object>> findProductDetailInfo(){
        return productMapper.selectProductDetailInfo();
    }

    /**
     * 根据商品id获取此商品的所有图片
     * @param productId
     * @return
     */
    @Override
    public List<String> findProductImgByProductId(String productId){
        return productMapper.selectProductImgByProductId(productId);
    }

}
