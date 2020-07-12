package com.huangyichun.taskManager.dao;

import com.huangyichun.core.dao.BaseDao;
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
    /**
     * 按任务类型查询
     *
     * @param type 任务类型码
     * @return 该类型任务对象的列表
     * @author: xuyf
     * @Date: 2020/7/5
     */
    List<Task> selectByType(@Param("type") String type);
}