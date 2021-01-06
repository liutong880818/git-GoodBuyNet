package com.java.service;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/11/10 13:10
 * author: LT
 */
public interface TransverseNavService {

    /**
     * 获取8个横向导航栏信息
     * @return
     */
    List<Map<String,Object>> findTransverseNavs();
}
