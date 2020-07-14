package com.huangyichun.bilibili.controller;


import com.huangyichun.bilibili.service.GetBilibiliCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.util.UUID;

@Controller
public class BilibiliController {

    @Value("${bilibili.png-save-path}")
    private String pngSavePath;


    @Autowired
    private GetBilibiliCookies getBilibiliCookies;

    @ResponseBody
    @RequestMapping("/bilibili/png/{uuid}")
    public String getQR(@PathVariable("uuid") String uuid) {
        return "<p>" + uuid + "</p> <img src=/bilibili/tempPNG/"+uuid+".png>";

    }

    @ResponseBody
    @RequestMapping("/bilibili/test")
    public String test(){
        String uuid = UUID.randomUUID().toString();


        return "<a href=/bilibili/png/\" +uuid+\"/> 跳跳跳</a>";

    }

    @ResponseBody
    @RequestMapping("/bilibili/test")
    public String login() throws FileNotFoundException {
        String uuid = UUID.randomUUID().toString();


        return "<a href=/bilibili/png/" +uuid+"/> 跳跳跳</a><br> <p>" + getBilibiliCookies.getBilibiliCookies(uuid) + "</p>";

    }


}
