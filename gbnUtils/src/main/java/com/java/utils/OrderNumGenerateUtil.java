package com.java.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Description: 描述
 * Date: 2020/12/28 21:21
 * author: LT
 */
public class OrderNumGenerateUtil {

    //生成20位订单编号
    public static String generateOrderNum(String userId) throws Exception {
        int num = 12;
        int[] arr = new int[num];
        Random random = new Random();
        //18个随机数
        String[] digital= {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h"};
        //日期 +  8位随机数（数字 + 字母）
        String prefix = new SimpleDateFormat("yyyyMMdd").format(new Date());
        byte[] md5 = MessageDigest.getInstance("MD5").digest(userId.getBytes("utf-8"));
        String orderNo = "";
        for(int i=0; i<num; i++){
            int temp = md5[i];
            if(temp<0){
                temp+=256;
            }
            int j = temp % 18;
            arr[i] = j;
            System.out.print(arr[i]+ "  ");
        }
        int randomNumber;
        for(int i=0; i<num; i++){
            randomNumber = arr[(int)(Math.random()*num)];
            orderNo = orderNo + digital[randomNumber];
        }
        return prefix+orderNo;
    }

    public static void main(String[] args) throws Exception {
        String s = generateOrderNum("1");
        System.out.println(s);
    }

}
