package com.huangyichun.bilibili.eventListener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class BilibiliLogInEventListener extends AbstractWebDriverEventListener {

    /**
     * 监听跳转的网页信息
     * @param url 跳转url
     * @param driver 哪个driver
     */
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

        System.out.println(url);
    }

}
