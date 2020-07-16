package com.huangyichun.wangyiyun.controller;


import com.huangyichun.bilibili.service.GetBilibiliCookies;
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
    private GetBilibiliCookies getBilibiliCookies;


    @ResponseBody
    @RequestMapping("/wangyiyun/test")
    public String wangyiyunTest(@PathVariable("uuid") String uuid) {
        return "<p>" + uuid + "</p>";

    }

}
