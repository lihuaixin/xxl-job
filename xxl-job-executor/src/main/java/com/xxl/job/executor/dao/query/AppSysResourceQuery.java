package com.xxl.job.executor.dao.query;

import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 资源配置表查询对象
 * </p>
 *
 * @author chenlei
 * @since 2020-04-14
 */
@Data
public class AppSysResourceQuery {

    /**
     * 配置类型 0系统配置  1业务配置
     */
    private Integer type;

    /**
     * key
     */
    private String qKey;

    /**
     * key描述
     */
    private String qDescribe;

    /**
     * key对应的值
     */
    private String value;

    /**
     * key对应的值1
     */
    private String value1;

    /**
     * key对应的值2
     */
    private String value2;

    /**
     * 创建时间
     */
    private Date gmtCreateBegin;

    private Date gmtCreateEnd;

    /**
     * 修改时间
     */
    private Date gmtModifyBegin;

    private Date gmtModifyEnd;


}
