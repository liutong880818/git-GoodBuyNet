package com.java.service;

import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/22 23:58
 * author: LT
 */
public interface BannerService {
    /**
     * 获取最新8张轮播图片
     * @return
     */
    Map<String,Object> findBanner();
}
