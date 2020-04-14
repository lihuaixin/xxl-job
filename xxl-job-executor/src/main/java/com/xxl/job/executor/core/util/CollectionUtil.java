package com.xxl.job.executor.core.util;

import java.util.List;
import java.util.Map;

/**
 * @desc: 集合工具类
 * @author: zhangyu
 * @date: 2018/8/3 10:16
 */
public class CollectionUtil {

    /**
     * To judge whether the List is empty
     * if the list is null or the size of this list is 0, then return true;
     *
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() < 1) {
            return true;
        }
        return false;
    }

    /**
     * To judge whether the List is not empty
     * if the list is not null or the size of this list > 0, then return true;
     *
     * @param list
     * @return
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * To judge whether the map is empty
     * if the list is null or the size of this list is 0, then return true;
     *
     * @param <K>
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> maps) {
        if (maps == null || maps.size() < 1) {
            return true;
        }
        return false;
    }

    /**
     * To judge whether the map is not empty
     * if the list is not null or the size of this list > 0, then return true;
     *
     * @return
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> maps) {
        if (maps != null && maps.size() > 0) {
            return true;
        }
        return false;
    }

}
