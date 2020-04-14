package com.xxl.job.executor.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @description：
 * @author：liqiang
 * @date：2019-06-03 10:28
 * @warning：本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface BaseService<TEntity> extends IService<TEntity> {


    /**
     * 根据查询参数分页查询
     * @param query
     * @param <TQuery>
     * @return
     */
    <TQuery> IPage<TEntity> pageQuery(PageQueryWrapper<TQuery> query);
}
