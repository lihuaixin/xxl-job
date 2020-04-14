package com.xxl.job.executor.api.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;

import java.util.List;


/**
 *@desc: 统一封装 处理响应类
 *@author  weiqigeng
 *@date  2018/7/9 16:52
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class HandleResponse extends BaseResponse {

    private String id;
    private int code;
    private String msg;
    private Object data;
    /** 当前请求路径 */
    private String path;



    private HandleResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }





    /**
     * 全局统一异常处理封装失败的响应
     *
     * @return
     */
    public static HandleResponse failedResponseForGlobal(int code, String msg, Object data) {
        return new HandleResponse(code, msg, data);
    }


    /**
     * 封装失败的响应
     *
     * @return
     */
    public static HandleResponse failedResponse(String msg) {
        return new HandleResponse(400, msg, null);
    }


}
