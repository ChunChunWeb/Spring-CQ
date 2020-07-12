package com.huangyichun.taskManager.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Task(数据库访问对象)
 *
 * @author xuyf
 * @date 2020-07-03 16:45:42
 */
@Data
@Entity
@Table(name = "task")
@Component
public class Task implements Serializable {

	/**
	 * 任务id
	 * nullable : false
	 * default  : null
	 */
	@Id
	@Column(name = "id", nullable = true, length = 32)
	private String id;

	/**
	 * 任务名
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "job_name", nullable = true, length = 255)
	private String jobName;

	/**
	 * 任务描述
	 * nullable : true
	 * default  : 'NULL'
	 */
	@Column(name = "description", nullable = true, length = 255)
	private String description;

	/**
	 * cron表达式
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "cron_expression", nullable = true, length = 255)
	private String cronExpression;

	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "bean_class", nullable = true, length = 255)
	private String beanClass;

	/**
	 * 任务状态
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "job_status", nullable = true, length = 255)
	private String jobStatus;

	/**
	 * 任务分组
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "job_group", nullable = true, length = 255)
	private String jobGroup;

	/**
	 * 创建者
	 * nullable : true
	 * default  : 'NULL'
	 */
	@Column(name = "create_user", nullable = true, length = 64)
	private String createUser;

	/**
	 * 创建时间
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "create_time", nullable = true)
	private java.util.Date createTime;

	/**
	 * 更新者
	 * nullable : true
	 * default  : 'NULL'
	 */
	@Column(name = "update_user", nullable = true, length = 64)
	private String updateUser;

	/**
	 * 更新时间
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "update_time", nullable = true)
	private java.util.Date updateTime;
}
