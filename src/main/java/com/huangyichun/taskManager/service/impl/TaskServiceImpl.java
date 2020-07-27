package com.huangyichun.taskManager.service.impl;

import com.huangyichun.core.util.StringUtil;
import com.huangyichun.taskManager.dao.TaskDao;
import com.huangyichun.taskManager.entity.TaskVO;
import com.huangyichun.taskManager.ienum.TaskEnum;
import com.huangyichun.taskManager.model.Task;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (TaskVO)表服务实现类
 *
 * @author xugg001
 * @since 2020-07-03 15:58:05
 */
@Service("taskService")
public class TaskServiceImpl {
    @Resource
    private TaskDao taskDao;

    @Autowired
    @Qualifier("jobScheduler")
    private Scheduler jobScheduler;

    public void addTask(TaskVO taskVO) {
        System.out.println("----------新建任务----------");
        Task task = new Task();
        BeanUtils.copyProperties(taskVO, task);
        //设置任务配置信息
        task.setId(StringUtil.getUUID());
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        System.out.println("addtask============>" + task);
//        taskDao.insertSelective(task);
        taskDao.insert(task);
    }

    public void deleteTask(String taskId) {
        Task task = taskDao.selectByPrimaryKey(taskId);
        //修改任务状态为已删除
        task.setJobStatus(TaskEnum.STATUS.DELETED.getCode());
        taskDao.updateByPrimaryKeySelective(task);
    }

    public void stopTask(String taskId) {
        Task task = taskDao.selectByPrimaryKey(taskId);
        //修改任务状态为停止
        task.setJobStatus(TaskEnum.STATUS.STOP.getCode());
        taskDao.updateByPrimaryKeySelective(task);
    }

    public void scheduleJob(Task task) {
        JobDetail jobDetail;
    }
}