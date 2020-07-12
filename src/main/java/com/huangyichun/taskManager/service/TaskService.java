package com.huangyichun.taskManager.service;

import com.huangyichun.taskManager.entity.TaskVO;

import java.util.List;

/**
 * (TaskVO)表服务接口
 *
 * @author xugg001
 * @since 2020-07-03 15:58:04
 */
public interface TaskService {
    /**
     * 新增任务到数据库
     *
     * @param taskVO
     * @return
     * @author: xuyf
     * @Date: 2020/7/3
     */
    void addTask(TaskVO taskVO);

    /**
     * 根据任务id删除任务
     *
     * @param taskId 任务id
     * @author: xuyf
     * @Date: 2020/7/3
     */
    void deleteTask(String taskId);

    /**
     * 修改任务
     *
     * @param taskVO
     * @return
     * @author: xuyf
     * @Date: 2020/7/3
     */
    void modifyTask(TaskVO taskVO);

    /**
     * 停止一个任务
     *
     * @param taskId 任务id
     * @author: xuyf
     * @Date: 2020/7/3
     */
    void stopTask(String taskId);

    /**
     * 获取可以用于配置的task类名
     *
     * @param
     * @return
     * @author: xuyf
     * @Date: 2020/7/10
     */
    <T> List<T> getTaskClass();
}

