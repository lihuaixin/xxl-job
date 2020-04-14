package com.xxl.job.executor.dao.domain;

import java.time.LocalDateTime;

import com.xxl.job.executor.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源配置表
 * </p>
 *
 * @author chenlei
 * @since 2020-04-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppSysResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModify;


}
