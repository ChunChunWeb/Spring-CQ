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
     * @param taskType
     * @return
     * @author: xuyf
     * @Date: 2020/7/3
     */
    public List<Task> scanTask(TaskEnum.type taskType) {

        return null;
    }
}
