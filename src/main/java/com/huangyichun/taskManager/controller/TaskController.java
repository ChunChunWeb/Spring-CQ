package com.huangyichun.taskManager.controller;

import com.huangyichun.core.MyTask;
import com.huangyichun.core.util.BeanTool;
import com.huangyichun.taskManager.dao.TaskDao;
import com.huangyichun.taskManager.entity.TaskVO;
import com.huangyichun.taskManager.service.TestJob;
import com.huangyichun.taskManager.service.impl.QuartzUtil;
import com.huangyichun.taskManager.service.impl.TaskServiceImpl;
import lombok.Data;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * (TaskVO)表控制层
 *
 * @author xugg001
 * @since 2020-07-03 15:58:06
 */
@RestController
@RequestMapping("task")
@PropertySource("/quartz.properties")
public class TaskController {
    private final ApplicationContext applicationContext = BeanTool.getApplicationContext();

    /**
     * 服务对象
     */
    @Autowired
    private TaskServiceImpl taskService;

    /**
     * 任务调度对象
     */
    @Autowired
    private QuartzUtil quartzUtil;

    @Autowired
    private TaskDao taskDao;

    @Qualifier("jobScheduler")
    @Autowired
    private Scheduler jobschduler;

    /**
     * 新增任务
     *
     * @param taskVO 任务参数
     * @return
     * @author: xuyf
     * @Date: 2020/7/16
     */
    @GetMapping("add")
    @ResponseBody
    public void addTask(@RequestBody TaskVO taskVO) {
        System.out.println("controller=======>" + taskVO);
        taskService.addTask(taskVO);
    }

    /**
     * 删除任务
     *
     * @param taskId 任务id
     * @return
     * @author: xuyf
     * @Date: 2020/7/16
     */
    @GetMapping("delete/{taskId}")
    public void deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
    }

    /**
     * 暂停任务
     *
     * @param taskId 任务id
     * @return
     * @author: xuyf
     * @Date: 2020/7/16
     */
    @GetMapping("stop/{taskId}")
    public void stopTask(@PathVariable String taskId) {
        taskService.stopTask(taskId);
    }

    @GetMapping("task-list")
    @ResponseBody
    public String[] getTaskList() {
        Map<String, Object> tasks = applicationContext.getBeansWithAnnotation(MyTask.class);
        for (String key : tasks.keySet()) {
            System.out.println("任务类：" + tasks.get(key));
        }
        String[] tasks1 = applicationContext.getBeanNamesForAnnotation(MyTask.class);
        for (String name : tasks1) {
            System.out.println("任务名：" + name);
            System.out.println("根据任务名获取类：" + applicationContext.getBean(name));
        }
        return applicationContext.getBeanNamesForAnnotation(MyTask.class);
    }

    @GetMapping("test")
    public void test() throws SchedulerException {
        System.out.println("test========" + applicationContext.getBean("taskDao"));
        quartzUtil.runJob(taskDao.queryByCron("").get(0), jobschduler);
//        System.out.println(taskDao.queryByCron(TaskEnum.STATUS.RUNNING.getCode()));
//        Task task = new Task();
//        task.setId(StringUtil.getUUID());
//        task.setBeanClass("测试任务");
//        task.setJobName("测试任务1");
//        task.setJobGroup("测试组");
//        task.setCronExpression("0/2 * * * * ? ");
//        System.out.println(task);
    }

    @GetMapping("test1")
    public void test1() throws SchedulerException, InterruptedException {
        System.out.println("method------test1");
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = newJob(TestJob.class).withIdentity("test", "test1").build();
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/3 0/3 * * * ?")
                ).forJob("test", "test1")
                .build();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
//        scheduler.start();
        System.out.println("scheduler started........");
        Thread.sleep(60000);
        scheduler.shutdown();
        System.out.println("调度结束");
    }
}

@MyTask
@Data
@Component("A任务")
class AclassTest implements Job {
    String name;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("A任务执行");
    }
}

@MyTask
@Data
@Component("B任务")
class BclassTest implements Job {
    String name;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("B任务执行");
    }
}