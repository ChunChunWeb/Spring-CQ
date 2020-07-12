package com.huangyichun.taskManager.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * (TaskVO)实体类
 *
 * @author xugg001
 * @since 2020-07-03 15:48:32
 */
@Data
@Component
public class TaskVO implements Serializable {
    private static final long serialVersionUID = -37588618554585189L;

    private Long id;
    /**
     * 任务名
     */
    private String jobName;
    /**
     * 任务描述
     */
    private String description;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;
    /**
     * 任务状态
     */
    private String jobStatus;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Date updateTime;
}