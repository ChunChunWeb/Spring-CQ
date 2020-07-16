package com.huangyichun.wangyiyun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huangyichun.auto_cq.utils.ParseUtil;
import com.huangyichun.auto_cq.utils.WeatherUtil;
import com.huangyichun.wangyiyun.service.WangYiYunServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Hadi
 * @date 2020年7月16日
 */


@Service
public class WangYiYunServerImpl implements WangYiYunServer {

    static final String API_URL = "http://huangyichun.com/wangyiyun/";
    static final String CHROME_DRIVER_NAME = "webdriver.chrome.driver";


    @Value("${bilibili.chrome-driver-path}")
    private String CHROME_DRIVER_PATH;

    /**
     * 打卡服务，传入账号密码进行打卡
     * @param phoneNumberString 手机号
     * @param pwdString 密码
     *
     */
    public  String login(String phoneNumberString, String pwdString) {
        if (CHROME_DRIVER_PATH == null) {
            CHROME_DRIVER_PATH = "C:/modules/chrome/chromedriver_win83.exe";
        }


        // 每日循环打卡
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("uin", phoneNumberString);
        userMap.put("pwd", pwdString);

        JSONObject jsonObject = new JSONObject(userMap);
        System.out.println(jsonObject.toString());

        System.getProperties().setProperty(CHROME_DRIVER_NAME, CHROME_DRIVER_PATH);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");         //无界面参数
        chromeOptions.addArguments("no-sandbox");       //禁用沙盒 就是被这个参数搞了一天
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(API_URL);

        // 填写信息
        WebElement uin = webDriver.findElement(By.id("uin"));
        uin.sendKeys(new String[]{userMap.get("uin").toString()});
        WebElement pwd = webDriver.findElement(By.id("pwd"));
        pwd.sendKeys(new String[]{userMap.get("pwd").toString()});

        webDriver.findElement(By.id("submit")).click();

        WebDriverWait waitTenSecond = new WebDriverWait(webDriver, 10);

        try {
            // 寻找
            WebElement daka = waitTenSecond.until(ExpectedConditions.visibilityOfElementLocated(By.id("daka")));
            WebElement sign = waitTenSecond.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign")));
            WebElement animated = waitTenSecond.until(ExpectedConditions.visibilityOfElementLocated(By.className("animated")));

            System.out.println(animated.getText());
            for (int i = 0; i < 5; i++) {

                sign.click();
                daka.click();
                while (!sign.getText().equals("签到失败:重复签到")) {
                    sign.click();
                }
                Thread.sleep(30000L);
            }

            System.out.println(daka.getText());
        } catch (Exception e) {
            System.out.println(e);
        }

        webDriver.close();
        webDriver.quit();
        return "";
    }



    public static void main(String[] args) throws InterruptedException, IOException {
        new WangYiYunServerImpl().login("18408235857", "499154897");
    }
}
