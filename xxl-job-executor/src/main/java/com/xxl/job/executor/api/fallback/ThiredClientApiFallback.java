package com.xxl.job.executor.api.fallback;


import com.xxl.job.executor.core.common.FallbackHandle;
import com.xxl.job.executor.api.client.ThiredClientApi;
import com.xxl.job.executor.api.response.HandleResponse;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThiredClientApiFallback implements FallbackFactory<ThiredClientApi> {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FallbackHandle fallbackHandle;

    @Override
    public ThiredClientApi create(Throwable throwable) {
        return new ThiredClientApi() {
            @Override
            public HandleResponse test() {
                log.error("服务降级啦 authBindCard");
                this.handleException();
                return HandleResponse.failedResponse("authBindCard 服务降级啦");

            }


            /**
             * 统一处理异常
             */
            private void handleException(){
                if(throwable instanceof RuntimeException){
                    log.error("ThiredClientApiFallback,throwable={}",throwable);
                    throw new RuntimeException();
                }
            }
        };
    }
}
