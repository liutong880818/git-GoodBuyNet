package com.java.service.impl;

import com.java.mapper.NavMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/11/4 14:26
 * author: LT
 */
@Service
public class NavServiceImpl implements com.java.service.NavService {

    @Autowired
    private NavMapper navMapper;

    /**
     * 获取分页横向导航栏
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String,Object> findNavs(int pageNum, int pageSize){
        //1、查询总记录数
        int totalCount = navMapper.selectTotalCount("1");
        //2、查询分页好后的数据
        List<Map<String, Object>> rows = navMapper.selectNavs((pageNum - 1) * pageSize, pageSize);
        //3、将结果封装成easyui需要的格式
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total",totalCount);
        resultMap.put("rows",rows);
        return resultMap;
    }

    /**
     * 添加一条横向导航栏信息
     * @param title
     * @param url
     * @return
     */
    @Override
    public Map<String, Object> saveNav(String title, String url) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","1");
        //1、数据校验
        if(title==null||url==null){
            resultMap.put("msg","数据不能为空");
            return resultMap;
        }
        if(!title.matches("[\\u4e00-\\u9fa5]{1,5}")||!url.matches("[a-zA-z]+://[^\\s]*")){
            resultMap.put("msg","数据格式不正确");
            return resultMap;
        }
        //2、调用dao层
        int flag = navMapper.insertNav(title, url, "1");
        if(flag==1){
            resultMap.put("status","0");
            return resultMap;
        }else {
            resultMap.put("msg","后台小哥出差了");
            return resultMap;
        }
    }

    /**
     * 根据id查询某个指定横向导航栏信息
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findTransverseNavById(String id) {
        //1、数据校验
        return navMapper.selectTransverseNavById(id);
    }

    /**
     * 根据id修改某个指定横向导航栏信息
     * @param title
     * @param url
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> modifyTransverseNavById(String title, String url, String id) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","1");
        //1、数据校验
        if(title==null||url==null||id==null){
            resultMap.put("msg","数据不能为空");
            return resultMap;
        }
        if(!title.matches("[\\u4e00-\\u9fa5]{1,5}")||!url.matches("[a-zA-z]+://[^\\s]*")||!id.matches("[1-9]\\d*")){
            resultMap.put("msg","数据格式不正确");
            return resultMap;
        }
        //2、调用dao层
        int flag = navMapper.updateTransverseNavById(title, url, id);
        if(flag==1){
            resultMap.put("status","0");
            return resultMap;
        }else {
            resultMap.put("msg","后台小哥出差了");
            return resultMap;
        }
    }

    /**
     * 根据id删除某个指定横向导航栏信息
     * @param idStr
     * @return
     */
    @Override
    public Map<String, Object> removeTransverseNavById(String idStr) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","1");
        //1、数据校验
        if(idStr==null||!idStr.matches("([1-9]\\d*,)+")){
            resultMap.put("msg","数据不能为空或者数据格式不正确");
            return resultMap;
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("idStr",idStr+"0");
        int flag = navMapper.deleteTransverseNavById(paramMap);
        System.out.println(flag);
        if(flag==0){
            resultMap.put("msg","后台小哥出差了");
            return resultMap;
        }else {
            resultMap.put("status","0");
            return resultMap;
        }
    }


}
