package com.java.service.impl;

import com.java.utils.MongoUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/18 13:51
 * author: LT
 */
@Service
public class DiscussServiceImpl implements com.java.service.DiscussService {

    private MongoUtil mongoUtil = new MongoUtil();

    /**
     * 获取评论信息
     * @param productNum
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String,Object> findDiscuss(String productNum, int pageNum, int pageSize){
        //1、数据校验
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//获取评论成功
        //2、从mongodb中获取数据
        mongoUtil.start();
        MongoCollection<Document> collection = mongoUtil.collection;
        FindIterable<Document> docs = collection.find(Document.parse("{\"productNum\":\""+productNum+"\"}"));
        FindIterable<Document> docPage = docs.skip((pageNum - 1) * pageSize).limit(pageSize);
        //3、将数据封装到map集合中去
        List<Document> discussList = new ArrayList<>();
        for(Document doc : docPage){
            discussList.add(doc);
        }
        //4、关闭mongodb链接
        mongoUtil.close();
        resultMap.put("discussList",discussList);
        return resultMap;
    }

    /**
     * 添加评论信息
     * @param paramMap
     * @return
     */
    @Override
    public Map<String, Object> saveDiscuss(Map<String, Object> paramMap) {
        //1、数据校验
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//获取评论成功
        //2、连接mongodb
        try {
            mongoUtil.start();
            MongoCollection<Document> collection = mongoUtil.collection;
            //3、添加数据
            collection.insertOne(new Document(paramMap));
            //关闭
            mongoUtil.close();
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","1");//添加评论失败
            resultMap.put("msg","后台小哥出差了");
            return resultMap;
        }
    }

}
