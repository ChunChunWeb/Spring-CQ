package com.huangyichun.wangyiyun.controller;


import com.huangyichun.bilibili.service.GetBilibiliCookies;
import com.huangyichun.wangyiyun.service.WangYiYunServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WangYiYunController {

    @Value("${bilibili.png-save-path}")
    private String pngSavePath;


    @Autowired
    private WangYiYunServer wangYiYunServer;


    @ResponseBody
    @RequestMapping("/wangyiyun/test/{uin}/{pwd}")
    public String wangyiyunTest(@PathVariable("uin") String uuid, @PathVariable("pwd") String pwd) {

        wangYiYunServer.login(uuid, pwd);
        return "<p>" + uuid + " " + pwd + "</p>";
    }

}
