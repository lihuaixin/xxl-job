package com.xxl.job.executor.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @description：通用的分页查询入参
 * @author：liqiang
 * @date：2019-06-04 20:29
 * @warning：本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class PageQueryWrapper<TQuery> {

    private Class<TQuery> queryClazz;

    public PageQueryWrapper() {
        this.page = new Page();
    }

    private TQuery queryBody;
    private Page page;

    public TQuery getDefaultQueryBody() {
        if (queryClazz == null) {
            return null;
        }
        TQuery query = null;
        try {
            query = queryClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return query;
    }

    public Class<TQuery> getQueryClazz() {
        return queryClazz;
    }

    public void setQueryClazz(Class<TQuery> queryClazz) {
        this.queryClazz = queryClazz;
    }

    public TQuery getQueryBody() {
        return queryBody;
    }

    public void setQueryBody(TQuery queryBody) {
        this.queryBody = queryBody;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
