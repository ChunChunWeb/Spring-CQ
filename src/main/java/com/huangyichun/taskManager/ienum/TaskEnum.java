package com.huangyichun.taskManager.ienum;

import com.huangyichun.core.ienum.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 任务类相关枚举，想到就加
 *
 * @author xuyf
 * @date 2020/7/3
 */
@Component
public class TaskEnum {
    /**
     * 任务状态枚举
     *
     * @author xuyf
     * @date 2020/7/3
     */
    @Getter
    @AllArgsConstructor
    public enum STATUS implements IEnum<String> {
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
    public enum TYPE implements IEnum<String> {
        PLUGIN("1", "插件任务"),
        QQ_MSG("2", "qq定时消息"),
        PUBLIC("3", "公有任务");
        private final String code;
        private final String text;
    }


}
