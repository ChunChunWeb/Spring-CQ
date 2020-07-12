package com.huangyichun.bilibili.config;


import com.huangyichun.bilibili.service.GetBilibiliCookies;
import com.huangyichun.bilibili.service.impl.GetBilibiliCookiesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BiliBiliConfig implements WebMvcConfigurer {
    @Value("${bilibili.png-save-path}")
    private String pngSavePath;


    /**
     * 配置资源映射
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/bilibili/tempPNG/**")
                .addResourceLocations("file:" + pngSavePath);


    }

}
