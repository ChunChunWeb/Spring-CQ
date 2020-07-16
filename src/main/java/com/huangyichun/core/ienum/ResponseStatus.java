package com.huangyichun.core.ienum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus implements IEnum<String> {
    FAIL("100", "失败"),

    SUCCESS("200", "成功"),

    LOGERROR("300", "登录错误"),

    NOTFOUND("400", "未找到资源");

    String code;
    String text;
}
