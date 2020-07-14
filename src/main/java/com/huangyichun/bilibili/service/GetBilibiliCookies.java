package com.huangyichun.bilibili.service;

import com.huangyichun.bilibili.ienum.BilibiliEnum;

import java.io.FileNotFoundException;

public interface GetBilibiliCookies {

    BilibiliEnum.RqStatus getBilibiliCookies(String uuid) throws FileNotFoundException;

}
