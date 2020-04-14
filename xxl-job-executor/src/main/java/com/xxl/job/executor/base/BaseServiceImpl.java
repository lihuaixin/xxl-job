package com.xxl.job.executor.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @description：
 * @author：
 * @date：2019-06-03 12:30
 */
public abstract class BaseServiceImpl<TDao extends BaseMapper<TEntity>, TEntity> extends ServiceImpl<TDao, TEntity> implements BaseService<TEntity> {

    @Override
    public <TQuery> IPage<TEntity> pageQuery(PageQueryWrapper<TQuery> query) {
        BaseDao dao = (BaseDao) baseMapper;
        TQuery queryBody = query.getQueryBody();
        if (queryBody == null) {
            queryBody = query.getDefaultQueryBody();
        }
        query.getPage().setRecords(dao.pageQuery(queryBody, query.getPage()));
        return query.getPage();
    }
}
