package com.huangyichun.taskManager.ienum;

import com.huangyichun.coreUtil.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类相关枚举，想到就加
 *
 * @author xuyf
 * @date 2020/7/3
 */
public class TaskEnum {
    /**
     * 任务状态枚举
     *
     * @author xuyf
     * @date 2020/7/3
     */
    @Getter
    @AllArgsConstructor
    public enum status implements IEnum<String> {
        DELETED("-1", "已删除"),
        STOP("0", "已停止"),
        RUNNINR("1", "运行中");
        String code;
        String text;
    }

    /**
     * 任务类型
     *
     * @author xuyf
     * @date 2020/7/3
     */
    @Getter
    @AllArgsConstructor
    public enum type implements IEnum<String> {
        PLUGIN("1", "插件任务"),
        QQ_MSG("2", "qq定时消息"),
        OTHER("3", "其他任务");
        private String code;
        private String text;
    }
}
