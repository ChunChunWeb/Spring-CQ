package com.huangyichun.taskManager.dao;

import com.huangyichun.auto_cq.utils.BaseDao;
import com.huangyichun.taskManager.model.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TaskVO)表数据库访问层
 *
 * @author xugg001
 * @since 2020-06-30 10:34:01
 */
@Repository
public interface TaskDao extends BaseDao<Task> {

    List<Task> selectByType(@Param("type") String type);
}