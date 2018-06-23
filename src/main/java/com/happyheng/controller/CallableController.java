package com.happyheng.controller;

import com.alibaba.fastjson.JSON;
import com.happyheng.service.CallableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by happyheng on 2018/6/18.
 */
@RestController
@RequestMapping("/callable")
public class CallableController {

    @Autowired
    private CallableService callableService;

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {



        // 访问网站
        List<String> requestUrlList = new ArrayList<>();
        requestUrlList.add("https://www.baidu.com");
        requestUrlList.add("http://news.baidu.com");
        requestUrlList.add("http://www.qq.com");
        requestUrlList.add("http://www.sina.com.cn");
        requestUrlList.add("http://www.163.com");

        // 异步获取数据
        List<String> resultData = callableService.getCallableData(requestUrlList);

        return JSON.toJSONString(resultData);
    }

}
