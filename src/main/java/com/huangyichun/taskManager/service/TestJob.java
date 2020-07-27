package com.huangyichun.taskManager.service;

import com.huangyichun.core.MyTask;
import lombok.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

//测试执行job
@MyTask("testJob")
@Component("测试任务")
@Data
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("test job running---------------");
    }
}
