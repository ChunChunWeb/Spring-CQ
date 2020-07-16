package com.huangyichun.wangyiyun.service.impl;

import com.huangyichun.auto_cq.utils.ParseUtil;
import com.huangyichun.auto_cq.utils.WeatherUtil;
import com.huangyichun.wangyiyun.service.WangYiYunServer;
import org.springframework.stereotype.Service;

@Service
public class WangYiYunServerImpl implements WangYiYunServer {

    static final String API_URL = "http://huangyichun.com/wangyiyun/";

    @Override
    public String login(String user, String password) {
        // 有测试连接的么
        System.out.println(ParseUtil.doGet(API_URL));

        return null;
    }
}
