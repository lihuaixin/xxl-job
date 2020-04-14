package com.xxl.job.executor.base;



import com.xxl.job.executor.core.util.AesUtil;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable{

    private final static String pass = String.valueOf(Constants.AES_KEY);

    private String code;
    private String msg;
    private T data;

    public static <T> BaseResponse success(T t){
        return new BaseResponse<T>(Constants.SUCCEED_CODE_VALUE, "成功", t);
    }

    public static <T> BaseResponse fail(String msg){
        if(null == msg) {
            msg = "失败";
        }
        return new BaseResponse<T>(Constants.FAIL_CODE_VALUE, msg, null);
    }


    public BaseResponse(String succeedCodeValue, String succeedCodeValueMsg) {
    }

    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public String getCode() {
        return code;
    }

    public BaseResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }

    public String getTimeblock() {
        String time = String.valueOf(System.currentTimeMillis());
        return new String(AesUtil.encrypt(time,pass));
    }

    public void setTimeblock(String timeblock) {
        String timeblock1 = timeblock;
    }


}
