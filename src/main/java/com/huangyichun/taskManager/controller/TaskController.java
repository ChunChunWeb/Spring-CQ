package com.huangyichun.taskManager.controller;

import com.huangyichun.taskManager.entity.TaskVO;
import com.huangyichun.taskManager.service.impl.QuartzUtil;
import com.huangyichun.taskManager.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private TaskServiceImpl taskService;

    /**
     * 任务调度对象
     */
    @Autowired
    private QuartzUtil quartzUtil;

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

    @GetMapping("test")
    public String test() {
        return "test ";
    }
}