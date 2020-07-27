package com.huangyichun.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanTool implements ApplicationContextAware {
    /**
     * 上下文对象
     */
    private static ApplicationContext applicationContext;

    /**
     * 获取上下文对象
     *
     * @Return ApplicationContext 上下文对象
     * @Author: xuyf
     * @Date: 2020/7/19
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanTool.applicationContext = applicationContext;
    }

    /**
     * 设置上下文
     *
     * @Param applicationContext
     * @Return void
     * @Author: xuyf
     * @Date: 2020/7/19
     */
    public static void setContext(ApplicationContext applicationContext) throws BeansException {
        BeanTool.applicationContext = applicationContext;
    }

    /**
     * 获取使用了annotationClass的类的实例集合
     *
     * @Param annotationClass
     * @Return java.lang.String[]
     * @Author: xuyf
     * @Date: 2020/7/22
     */
    public static String[] getAnnotationClass(Class annotationClass) {
        return applicationContext.getBeanNamesForAnnotation(annotationClass);
    }

    /**
     * 根据bean名称获取bean
     *
     * @Param beanName bean名称
     * @Return java.lang.Object 获取的对象
     * @Author: xuyf
     * @Date: 2020/7/27
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

}
