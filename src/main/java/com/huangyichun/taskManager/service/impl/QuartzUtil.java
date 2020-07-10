package com.huangyichun.taskManager.service.impl;

import com.huangyichun.taskManager.ienum.TaskEnum;
import com.huangyichun.taskManager.model.Task;

import java.util.List;

/**
 * 任务调度控制
 * 等定时扫描重新运行任务还是修改后立即修改运行生效？
 *
 * @author xuyf
 * @date 2020/7/3
 */
public class QuartzUtil {

    /**
     * 获取一类任务的列表
     *
     * @param taskType 任务类型代码
     * @return
     * @author: xuyf
     * @Date: 2020/7/3
     */
    public List<Task> scanTasks(TaskEnum.type taskType) {
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

}
