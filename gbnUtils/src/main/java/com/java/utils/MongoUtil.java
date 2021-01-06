package com.java.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Description: 描述
 * Date: 2020/12/18 13:38
 * author: LT
 */
public class MongoUtil {

    public MongoCollection<Document> collection = null;
    private MongoClient client = null;
    private MongoDatabase db = null;

    public void start(){
        client = new MongoClient("127.0.0.1",27017);
        db = client.getDatabase("goodBuyNet");
        collection = db.getCollection("discuss");
    }

    public void close(){
        client.close();
    }
}
