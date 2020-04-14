package com.xxl.job.executor.api.client;



import com.xxl.job.executor.core.common.ServiceConstant;
import com.xxl.job.executor.api.config.ThiredClientApiConfig;
import com.xxl.job.executor.api.fallback.ThiredClientApiFallback;
import com.xxl.job.executor.api.response.HandleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = ServiceConstant.TAOCHE_THIRED_SERVICE,fallbackFactory = ThiredClientApiFallback.class,configuration = ThiredClientApiConfig.class)
public interface ThiredClientApi {

    /**
     * 调用此接口
     * @param
     * @return
     */
    @PostMapping("/third/test")
    HandleResponse test();

}

