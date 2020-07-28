package com.huangyichun.taskManager.config;

import com.huangyichun.taskManager.dao.TaskDao;
import com.huangyichun.taskManager.model.Task;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
@PropertySource("/quartz.properties")
public class QuartzConfigration {
    /**
     * 扫描数据库任务cron表达式
     * 每半个小时扫描一次
     */
    @Value("${scanPlan.cronExpression}")
    private String SCANPLAN;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TaskDao taskDao;

    // 指定quartz.properties，可在配置文件中配置相关属性
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public JobDetail scanTaskjobDetail() {
        System.out.println("初始化scanTaskjobDetail");
        Task scanTask = taskDao.selectByPrimaryKey("adminJob_01");
        Job clz = (Job) applicationContext.getBean(scanTask.getBeanClass());
        JobDetail jobDetail = newJob(clz.getClass())
                .withIdentity("scanTaskJob", "admin")
                .storeDurably(true)
                .build();
        return jobDetail;
    }

    /**
     * 主调度程序，负责定时扫描数据库任务
     *
     * @return 调度器对象
     * @throws SchedulerException
     */
    @Bean(name = "Masterscheduler")
    public Scheduler MasterScheduler() throws SchedulerException {
        System.out.println("初始化Masterscheduler");
        JobDetail jobDetail = scanTaskjobDetail();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        Trigger trigger = newTrigger()
                .withIdentity("scanPlanTrigger", "admin")
                .startNow()
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(SCANPLAN)
                ).forJob(jobDetail)
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        return scheduler;
    }

    @Bean("jobScheduler")
    public Scheduler jobScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }

    @Scheduled(cron = "0/3 * * * * ?")
    public void scanJob() {
        System.out.println("配置类定时扫描。。。" + taskDao.selectByCreater("xugg001"));
    }
}

