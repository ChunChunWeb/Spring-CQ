package com.huangyichun.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 任务类注解
 * 用于获取可以配置的任务类列表
 *
 * @author xuyf
 * @date 2020/7/10
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTask {
    String value() default "";
}
