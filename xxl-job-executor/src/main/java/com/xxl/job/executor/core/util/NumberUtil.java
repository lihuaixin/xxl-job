package com.xxl.job.executor.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc: 数字处理类
 * @author: zhangyu
 * @date: 2018/8/6 11:24
 */
public class NumberUtil {

    private static final Logger logger = LoggerFactory.getLogger(NumberUtil.class);

    public static int objToIntDefault(Object obj, int defaultValue) {
        if (null == obj || "".equals(obj)) return defaultValue;
        try {
            return Integer.parseInt(obj.toString().trim());
        } catch (Exception e) {
            handleChangeException("objToIntDefault,obj=" + obj + ",defaultValue=" + defaultValue, e);
            return defaultValue;
        }
    }

    public static Long objToLongDefault(Object obj, Long defaultValue) {
        if (null == obj || "".equals(obj)) {
            return defaultValue;
        }
        try {
            return Long.parseLong(obj.toString().trim());
        } catch (Exception e) {
            handleChangeException("objToLongDefault,obj=" + obj + ",defaultValue=" + defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * @describe: 统一处理解析异常
     * @param:
     * @return:
     * @author: weiqingeng
     * @date: 2018/6/7 13:47
     */
    private static void handleChangeException(String str, Exception e) {
        logger.info(str + " ,e=" + e);
    }

}
