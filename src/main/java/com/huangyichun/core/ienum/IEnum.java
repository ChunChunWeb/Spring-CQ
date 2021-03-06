package com.huangyichun.core.ienum;

/**
 * 枚举接口
 * 枚举类都应继承该接口，通过getCode，getText方法获取对应码值
 *
 * @author xuyf
 * @date 2020/7/16
 */
public interface IEnum<T> {

    static <E extends IEnum<T>, T> E parseByCode(T code, Class<E> type) {
        if (null != code && type.isEnum()) {
            for (E enumConstant : type.getEnumConstants()) {
                if (enumConstant.getCode().equals(code)) {
                    return enumConstant;
                }
            }
        }
        return null;
    }

    static <E extends IEnum<T>, T> E parseByCode(T code, Class<E> type, E defaultEnum) {
        return parseByCode(code, type) == null ? defaultEnum : parseByCode(code, type);
    }

    T getCode();

    String getText();
}
