package com.java.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.java.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description: 描述
 * Date: 2020/10/28 13:31
 * author: LT
 */
@Service
public class LoginServiceImpl implements com.java.service.LoginService {

    @Autowired
    private LoginMapper loginMapper;

    //redis缓存
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public Map<String,Object> findLogin(String username, String password){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");
        try {
            //1、数据校验：username：3-12位单词字符，密码：6-12位纯数字
            if(username==null||password==null){
                resultMap.put("status","1");
                resultMap.put("msg","用户名或密码不能为空");
                return resultMap;
            }
            if(!username.matches("\\w{3,12}")||!password.matches("\\d{6,12}")){
                resultMap.put("status","1");
                resultMap.put("msg","用户名或密码格式错误");
                return resultMap;
            }
            //2、查看数据在数据库中是否存在
            int flag = loginMapper.selectLogin(username, SecureUtil.md5(password));
            if(flag==0){
                resultMap.put("status","1");
                resultMap.put("msg","用户名或密码错误");
                return resultMap;
            }
            //3、将登录标记存放到redis中，并且失效时间为10min
            ValueOperations vop = redisTemplate.opsForValue();
            vop.set("username",username,10, TimeUnit.MINUTES);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","1");
            resultMap.put("msg","后台小哥出差了");
            return resultMap;
        }
    }

    /**
     * 查询登录的用户对应拥有的菜单栏信息
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> findMenus(String id) {
        //1、数据校验 id必须为大于0的正整数
        Object username = null;
        try {
            ValueOperations vop = redisTemplate.opsForValue();
            username = vop.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            username=null;
        }
        return loginMapper.selectMenus(username+"",id);
    }

}
