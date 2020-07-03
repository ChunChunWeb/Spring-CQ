package com.huangyichun.taskManager.controller;

import com.huangyichun.taskManager.service.TaskService;
import com.huangyichun.taskManager.service.impl.QuartzUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TaskVO)表控制层
 *
 * @author xugg001
 * @since 2020-07-03 15:58:06
 */
@RestController
@RequestMapping("task")
public class TaskController {
    /**
     * 服务对象
     */
    @Resource
    private TaskService taskService;

    /**
     * 任务调度对象
     */
    @Resource
    private QuartzUtil quartzUtil;

}