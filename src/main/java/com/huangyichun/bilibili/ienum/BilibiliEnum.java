package com.huangyichun.bilibili.ienum;


import com.huangyichun.core.ienum.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * bilibili 功能枚举
 *
 * @author Hadi
 * @date 2020/07/14
 */
public class BilibiliEnum {


    @Getter
    @AllArgsConstructor
    public enum RqStatus implements IEnum<Integer> {
        NEVER_SCAN(-1, "没有扫描"),
        NO_CONFIRM(-2, "没有确认"),
        CONFIRM(1, "登陆成功");
        Integer code;
        String text;
    }
}
