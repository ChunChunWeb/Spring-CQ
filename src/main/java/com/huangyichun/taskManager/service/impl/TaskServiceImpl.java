package com.huangyichun.taskManager.service.impl;

import com.huangyichun.coreUtil.StringUtil;
import com.huangyichun.taskManager.dao.TaskDao;
import com.huangyichun.taskManager.entity.TaskVO;
import com.huangyichun.taskManager.ienum.TaskEnum;
import com.huangyichun.taskManager.model.Task;
import org.springframework.beans.BeanUtils;
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

    public void addTask(TaskVO taskVO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskVO, task);
        //设置任务配置信息
        task.setId(StringUtil.getUUID());
        task.setUpdateTime(new Date());
        taskDao.insertSelective(task);
    }


    public void deleteTask(String taskId) {
        Task task = taskDao.selectByPrimaryKey(taskId);
        //修改任务状态为已删除
        task.setJobStatus(TaskEnum.status.DELETED.getCode());
        taskDao.updateByPrimaryKeySelective(task);
    }


    public void stopTask(String taskId) {
        Task task = taskDao.selectByPrimaryKey(taskId);
        //修改任务状态为停止
        task.setJobStatus(TaskEnum.status.STOP.getCode());
        taskDao.updateByPrimaryKeySelective(task);
    }
}