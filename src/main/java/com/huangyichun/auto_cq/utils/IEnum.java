package com.huangyichun.auto_cq.utils;

/**
 * 枚举接口
 * @param <T>
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
