package com.xxl.job.executor.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *@desc: 响应 基类
 *@author  weiqingeng
 *@date  2018/7/9 16:51
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BaseResponse implements Serializable {

    /** 服务器对应的代号 */
    private String xcode ="A102";

    /** 是否分页1: 是 0：否 */
    private int page;
    /** 数据总条数 */
    private long total;
    /** 页码*/
    private int pageNum;
    /** 每页数据量 */
    private int pageSize;
}
