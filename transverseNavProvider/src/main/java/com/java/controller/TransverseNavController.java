package com.java.controller;

import com.java.service.TransverseNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Description: 描述
 * Date: 2020/11/10 13:13
 * author: LT
 */
@Controller
@RequestMapping("/transverseNav")
public class TransverseNavController {

    @Autowired
    private TransverseNavService transverseNavService;

    @RequestMapping("/getTransverseNavs")
    public @ResponseBody List<Map<String,Object>> getTransverseNavs(){
        return transverseNavService.findTransverseNavs();
    }
}
