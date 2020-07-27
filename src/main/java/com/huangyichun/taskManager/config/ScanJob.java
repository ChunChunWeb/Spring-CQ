package com.huangyichun.taskManager.config;

import com.huangyichun.taskManager.model.Task;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("扫描任务")
public class ScanJob implements Job {

    /**
     * 30min内要执行的任务
     */
    public List<Task> todotTasks = new ArrayList<>();
    /**
     * 正在执行的任务
     */
    public List<Task> runningTasks = new ArrayList<>();
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 执行扫描任务
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        TaskDao taskDao = (TaskDao) BeanTool.getApplicationContext().getBean("taskDao");
//        System.out.println("执行扫描任务..." + BeanTool.getApplicationContext().getBean("taskDao"));
//        System.out.println(taskDao);
//        todotTasks = taskDao.selectByJobStatus(TaskEnum.STATUS.RUNNING.getCode());
//        System.out.println("运行状态的任务列表---------->" + taskDao.selectByJobStatus(TaskEnum.STATUS.RUNNING.getCode()));
    }
}
