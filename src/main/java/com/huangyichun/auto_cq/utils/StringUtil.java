package com.huangyichun.auto_cq.utils;

import java.util.UUID;

public class StringUtil {
    /**
     * 获取32位UUID
     *
     * @param
     * @return
     * @author: xuyf
     * @Date: 2020/7/3
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
