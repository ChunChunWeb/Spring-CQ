package com.huangyichun.core.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * dao层mybatis通用方法
 *
 * @author xuyf
 * @date 2020/7/5
 */
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
