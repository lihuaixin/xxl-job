package com.xxl.job.executor.base;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description：
 * @author：liqiang
 * @date：2019-06-03 10:27
 * @warning：本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface BaseDao<TEntity> extends BaseMapper<TEntity> {

    /**
     * 通用分页查询
     * @param query
     * @param page
     * @param <TQuery>
     * @return
     */
    <TQuery> List<TEntity> pageQuery(@Param("query") TQuery query, Page<TEntity> page);
}
