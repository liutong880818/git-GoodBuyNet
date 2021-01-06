package com.java.controller;

import com.yuqing.common.FastDFSClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/12/21 22:38
 * author: LT
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    /**
     * 提供文件上传的公共接口
     * @return
     */
    @RequestMapping("/fastDFSFileUpload")
    public Map<String,Object> fastDFSFileUpload(MultipartFile uploadFile){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//"0"代表文件上传成功
        try {
            //1、创建一个FastDFS连接对象
            FastDFSClient client = new FastDFSClient("classpath:resources/fdfs_client.conf");
            //2、获取上传文件的源文件名
            String oldName = uploadFile.getOriginalFilename();//文件名可能的形式： c:\\users\\public\\1.2.jpg
            //3、获取文件后缀名，例如：jpg png gif等
            String extName = oldName.substring(oldName.lastIndexOf(".") + 1);
            //4、开始上传文件
            //basePath=group1/M00/00/04/wKgZhVxp_zmAD0HzAADtVrCvlSc088.jpg
            String basePath = client.uploadFile(uploadFile.getBytes(), extName);
            //虚拟机的linux系统安装的fastfds，此linux的ip地址http://192.168.25.133/
            basePath = "http://192.168.25.133/" + basePath;
            System.out.println("basePath= " + basePath);
            resultMap.put("url",basePath);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","1");//"1"代表文件上传失败
            resultMap.put("msg","哎呀，后台小哥出差了");
            return resultMap;
        }
    }

}
