package com.xxl.job.executor.core.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;

import com.xxl.job.executor.core.util.AesUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 使用
 *@desc:  数据源配置
 *@author:  weiqingeng
 *@date:  2018/7/14 17:28
 */
@Configuration
@EnableTransactionManagement  // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(basePackages = {"com.taoche.dao.mapper"})
public class DataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    @NotNull
    @Value("${aes.password}")
    private String aesPassword;

    @NotNull
    @Value("${ishangjie.db.driverClass}")
    private String driverClass;

    @NotNull
    @Value("${ishangjie.db.url}")
    private String url;

    @NotNull
    @Value("${ishangjie.db.username}")
    private String username;

    @NotNull
    @Value("${ishangjie.db.password}")
    private String password;

    @Value("${ishangjie.db.maxActive}")
    private int maxActive;

    @Value("${ishangjie.db.initialSize}")
    private int initialSize;

    @Value("${ishangjie.db.maxWait}")
    private int maxWait;

    @Value("${ishangjie.db.minIdle}")
    private int minIdle;

    /**
     * 配置数据源,为了尊重老的加密方式，就直接使用老的方式进行解密
     * @return
     */
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(AesUtil.decrypt(url,aesPassword));
        dataSource.setUsername(AesUtil.decrypt(username,aesPassword));
        dataSource.setPassword(AesUtil.decrypt(password,aesPassword));
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setMinIdle(minIdle);

        List<Filter> filters = new ArrayList<>();
        StatFilter filter = new StatFilter();
        //慢sql，默认3s
        filter.setSlowSqlMillis(5000L);
        //增加sql统计的merge功能,默认是false
        filter.setMergeSql(true);
        //慢SQL日志记录
        filter.setLogSlowSql(true);
        filters.add(filter);
        dataSource.setProxyFilters(filters);

        return dataSource;
    }

    /**
     * 配置sqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, PageInterceptor pageHelper, PaginationInterceptor paginationInterceptor) {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        try {
            sqlSessionFactory.setDataSource(dataSource);
            sqlSessionFactory.setTypeAliasesPackage(env.getProperty("mybatis-plus.type-aliases-package"));
            sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis-plus.mapper-locations")));
            sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis-plus.config-location")));
            sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper, paginationInterceptor});//设置分页插件
            sqlSessionFactory.setVfs(SpringBootVFS.class);
        } catch (IOException e) {
            logger.error("sqlSessionFactory,e={}", e);
        }
        return sqlSessionFactory;

    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 配置分页插件
     *
     * @return
     */
    @Bean
    public PageInterceptor pageHelper() {
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        /** 该参数默认为false,设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用,和startPage中的pageNum效果一样 **/
        properties.setProperty("offsetAsPageNum", "true");
        /** 设置为true时，使用RowBounds分页会进行count查询,该参数默认为false **/
        properties.setProperty("rowBoundsWithCount", "true");
        /** 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是Page类型） <property name="pageSizeZero" value="true"/>
         3.3.0版本可用 - 分页参数合理化，默认false禁用
         启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
         **/
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}