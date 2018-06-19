package com.happyheng.callable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by happyheng on 2018/6/18.
 */
@RestController
@RequestMapping("/callable")
public class CallableController {


    @RequestMapping("/test")
    public String test(HttpServletRequest request) {

        return "test";
    }

}
