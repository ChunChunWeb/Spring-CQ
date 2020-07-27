package com.huangyichun;

import com.huangyichun.core.util.BeanTool;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.huangyichun.**.dao")
@ComponentScan(basePackages = "com.huangyichun.*")
public class DemoApplication {

    public static void main(String[] args) throws SchedulerException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        BeanTool.setContext(applicationContext);
    }
}
