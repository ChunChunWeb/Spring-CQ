package com.huangyichun.bilibili.service.impl;

import com.huangyichun.bilibili.eventListener.BilibiliLogInEventListener;
import com.huangyichun.bilibili.ienum.BilibiliEnum;
import com.huangyichun.bilibili.service.GetBilibiliCookies;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;


@Service
public class GetBilibiliCookiesImpl implements GetBilibiliCookies {


    @Value("${bilibili.png-save-path}")
    private String pngSavePath;

    @Value("${bilibili.chrome-driver-path}")
    private String CHROME_DRIVER_PATH;

    public final static String CHROME_DRIVER_NAME = "webdriver.chrome.driver";
    public final static int ONE = 1;
    public final static int SIXTY = 60;
    public final static String PNG_SUFFIX = ".png";

    public BilibiliEnum.RqStatus getBilibiliCookies(String uuid) throws FileNotFoundException {
        //  cookie
        StringBuilder stringBuilder = new StringBuilder();


        // 二维码编码
//        String uuid = java.util.UUID.randomUUID().toString();


        // 配置项
        System.getProperties().setProperty(CHROME_DRIVER_NAME, CHROME_DRIVER_PATH);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");         //无界面参数
        chromeOptions.addArguments("no-sandbox");       //禁用沙盒 就是被这个参数搞了一天

        //开启webDriver进程
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        // 监听事件
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);
        BilibiliLogInEventListener bilibiliEvent = new BilibiliLogInEventListener();
        eventFiringWebDriver.register(bilibiliEvent);


        // 开始等待被扫 及确认
        WebDriverWait waitOneSecond = new WebDriverWait(eventFiringWebDriver, ONE);
//        WebDriverWait waitSixtySecond = new WebDriverWait(eventFiringWebDriver, SIXTY);

        // 打开网页
        eventFiringWebDriver.get("https://passport.bilibili.com/login");
        eventFiringWebDriver.findElement(By.id("login-username")).sendKeys(new String[] {"233333333\r"});
        eventFiringWebDriver.findElement(By.id("login-passwd")).sendKeys(new String[] {"2333333\r"});
        eventFiringWebDriver.findElement(new By.ByClassName("btn-login")).click();

        // 截屏 并保存图片
        WebElement element = eventFiringWebDriver.findElement(By.className("qrcode-tips"));
        byte[] screenshotAs = element.getScreenshotAs(OutputType.BYTES);


        System.out.println("pngSavePath = " + pngSavePath);

        FileOutputStream fo = new FileOutputStream(new File(pngSavePath + uuid + PNG_SUFFIX));//文件输出流
        try {
            fo.write(screenshotAs, 0, screenshotAs.length);
            fo.close();
            System.out.println("下载完成 " + uuid);
        } catch (Exception e) {
            System.out.println("下载失败");
        }



        // 扫描确认复现框
        WebElement successElement = null;
        WebElement overdueElement = null;

        while (successElement == null && overdueElement == null) {
            try {
                // 寻找是否被扫描
                successElement = waitOneSecond.until(ExpectedConditions.visibilityOfElementLocated(By.className("success")));

            } catch (TimeoutException e) {
                System.out.println("还未扫描");
            }
            try {
                // 寻找是否超时
                overdueElement = waitOneSecond.until(ExpectedConditions.visibilityOfElementLocated(By.className("overdue")));

            } catch (TimeoutException e) {
                System.out.println("还未超时");
            }
        }

        if (successElement != null) {
            // 确认跳转了，完成
            System.out.println("已经扫描");

            Boolean until = null;
            int wait_counter = 0;
            while (until == null) {
                wait_counter ++;
                try {
                    until = waitOneSecond.until(ExpectedConditions.urlToBe("https://www.bilibili.com/"));
                } catch (TimeoutException e) {
                    System.out.println("等待用户确认");
                }

                if (wait_counter>60) {
                    break;
                }
            }

            if (until != null) {
                // 确认登录成功
                System.out.println("确认登录成功");

                System.out.println(until);

                // 打印cookie
                for (Cookie cookie : eventFiringWebDriver.manage().getCookies()) {
                    String name = cookie.getName();
                    String value = cookie.getValue();
                    System.out.println("name = " + name + "； value = " + value);
                    stringBuilder.append("name = ").append(name).append("； value = ").append(value).append("\n");
                }
            } else {
                // 未确认
                System.out.println("用户扫描,未确认，超时");
                return BilibiliEnum.RqStatus.NO_CONFIRM;
            }

        } else {
            // 未扫描
            System.out.println("用户未扫描，超时");
            return BilibiliEnum.RqStatus.NEVER_SCAN;
        }


        // 添加cookie流程
//        Cookie cookie = new Cookie("bili_jct","137226ad6b7ad57195c76c323f99deeb");
//        eventFiringWebDriver.manage().addCookie(cookie);

        webDriver.close();
        webDriver.quit();
        System.out.println(stringBuilder.toString());
        // 直接保存到数据库
        // 返回状态码
        return BilibiliEnum.RqStatus.CONFIRM;

    }





    public static void main(String[] args) throws FileNotFoundException {
        GetBilibiliCookiesImpl getBilibiliCookies = new GetBilibiliCookiesImpl();
        System.out.println(getBilibiliCookies.getBilibiliCookies(UUID.randomUUID().toString()));

    }



}
