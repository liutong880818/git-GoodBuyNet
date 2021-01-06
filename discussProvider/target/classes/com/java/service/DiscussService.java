package com.java.service;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/18 15:54
 * author: LT
 */
public interface DiscussService {
    /**
     * 获取评论信息
     * @param productNum
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String,Object> findDiscuss(String productNum, int pageNum, int pageSize);

    /**
     * 添加评论信息
     * @param paramMap
     * @return
     */
    Map<String,Object> saveDiscuss(Map<String,Object> paramMap);
}
