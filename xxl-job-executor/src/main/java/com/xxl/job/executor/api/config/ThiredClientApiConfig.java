package com.xxl.job.executor.api.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ThiredClientApiConfig {

    //@Value("${host.feign.request.connectTimeoutMillis}")
    private int connectTimeoutMillis = 20000;
    //@Value("${host.feign.request.readTimeoutMillis}")
    private int readTimeoutMillis = 20000;


    /*    *//**
     * 重试的相关配置,不重试
     *
     * @return
     */
    @Bean
    public Retryer thiredRetryer() {
        return Retryer.NEVER_RETRY;
    }

    /**
     * 读取与连接超时，有一个超时了都会降级
     *
     * @return
     */
    @Bean
    @Scope("prototype")
    public Request.Options thiredFeignOption() {
        Request.Options option = new Request.Options(connectTimeoutMillis, readTimeoutMillis);
        return option;
    }
}
