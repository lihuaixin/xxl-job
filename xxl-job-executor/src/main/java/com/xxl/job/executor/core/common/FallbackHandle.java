package com.xxl.job.executor.core.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @desc:  统一处理fallback异常
 * @author:
 * @date:  2018/7/31 16:28
 */
@Component
public class FallbackHandle {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 收集异常
     * @param throwable
     */
    public void handle(String fallbackName,Throwable throwable){
        log.error(fallbackName.concat(",throwable:{}"), throwable);
    }

}
