package com.happyheng.callable;

import com.happyheng.utils.HttpUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.Callable;

/**
 * 模拟访问的数据
 * Created by happyheng on 2018/6/23.
 */
public class RequestCallable implements Callable<String>{

    private String requestUrl;

    public RequestCallable(String requestUrl) {

        this.requestUrl = requestUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String call() throws Exception {

        if (StringUtils.isEmpty(requestUrl)) {
            return null;
        }

        return HttpUtils.get(requestUrl);
    }
}
