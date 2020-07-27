package com.huangyichun.taskManager.service.impl;

import com.huangyichun.core.util.BeanTool;
import com.huangyichun.taskManager.ienum.TaskEnum;
import com.huangyichun.taskManager.model.Task;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 任务调度控制
 * 等定时扫描重新运行任务还是修改后立即修改运行生效？
 *
 * @author xuyf
 * @date 2020/7/3
 */
@Component
public class QuartzUtil {
    /**
     * 获取一类任务的列表
     *
     * @param taskType 任务类型代码
     * @return
     * @author: xuyf
     * @Date: 2020/7/3
     */
    public List<Task> scanTasks(TaskEnum.TYPE taskType) {
        return null;
    }

    /**
     * 启动一个task
     *
     * @param task 要启动的任务
     * @return
     * @author: xuyf
     * @Date: 2020/7/5
     */
    public <T> void startTask(Task task) throws ClassNotFoundException {
        T t = (T) Class.forName(task.getBeanClass());

    }

    /**
     * 启动多个任务
     *
     * @param tasks 要启动的任务列表
     * @return
     * @author: xuyf
     * @Date: 2020/7/5
     */
    public void startTasks(List<Task> tasks) {

    }

    /**
     * 停止一个任务
     *
     * @param task
     * @return
     * @author: xuyf
     * @Date: 2020/7/5
     */
    public void stopTask(Task task) {

    }

    /**
     * 停止多个任务
     *
     * @param tasks
     * @return
     * @author: xuyf
     * @Date: 2020/7/5
     */
    public void stopTasks(List<Task> tasks) {

    }

    /**
     * 执行任务
     *
     * @Param task 任务Model
     * @Param scheduler 任务调度器
     * @Return java.util.Date   调度时间
     * @Author: xuyf
     * @Date: 2020/7/27
     */
    public Date runJob(Task task, Scheduler scheduler) throws SchedulerException {
        System.out.println(task);
        Job job = (Job) BeanTool.getBean(task.getBeanClass());
        JobDetail jobDetail = newJob(job.getClass())
                .withIdentity(task.getId(), task.getJobGroup())
                .build();
        Trigger trigger = newTrigger()
                .withIdentity(task.getId(), task.getJobGroup())
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(task.getCronExpression())
                ).forJob(jobDetail)
                .build();
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        return scheduler.scheduleJob(jobDetail, trigger);
    }

}
