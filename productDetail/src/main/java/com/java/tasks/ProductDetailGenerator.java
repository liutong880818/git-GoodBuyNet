package com.java.tasks;

import com.java.service.ProductService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/25 10:46
 * author: LT
 */
@Component
public class ProductDetailGenerator {

    @Autowired
    private ProductService productService;

    @Autowired
    private Configuration configuration;

    @Scheduled(cron = "0/5 * * * * *")
    public void generateProductDetail() throws Exception {
        List<Map<String, Object>> productDetailList = productService.findProductDetailInfo();
        Map<String,Object> resultMap = new HashMap<>();
        for(int i=0; productDetailList!=null && i<productDetailList.size(); i++){
            //1、取出某个商品的详细信息
            resultMap.put("productNum",productDetailList.get(i).get("productNum"));
            resultMap.put("title",productDetailList.get(i).get("title"));
            resultMap.put("subtitle",productDetailList.get(i).get("subTitle"));
            resultMap.put("price",productDetailList.get(i).get("price"));
            resultMap.put("type",productDetailList.get(i).get("type"));
            resultMap.put("color",productDetailList.get(i).get("color"));
            resultMap.put("imgUrl",productDetailList.get(i).get("imgUrl"));
            //2、取出指定productId商品的所有图片信息
            List<String> imgUrlList = productService.findProductImgByProductId(productDetailList.get(i).get("productId") + "");
            resultMap.put("imgUrlList",imgUrlList);
            //3、使用freemarker模板来生成html页面
            Template productTemplate = configuration.getTemplate("Product.ftl");
            //4、根据productTemplate生成html文件，生成好的文件存放到 D:\gbnViewProject\gbnProductDetail
            FileWriter writer = new FileWriter("D:\\gbnViewProject\\gbnProductDetail\\"+(productDetailList.get(i).get("productNum"))+".html");
            productTemplate.process(resultMap,writer);
            writer.close();
        }
    }

}
