package com.xxl.job.executor.core.util.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 *
 * @desc：线程池异步处理工具类  单例模式
 * @author weiqingeng
 * @date 2018年5月22日 上午10:15:06
 */
public class SingleThreadPool {
    private static ExecutorService threadPool;

    /**
     * 私有构造器，防止外部创建实例
     */
    private SingleThreadPool() {

    }

    /**
     * 创建1条线程的线程池
     */
    public static ExecutorService getThreadPool(){
        if(null == threadPool){
            synchronized (SingleThreadPool.class){
                threadPool = new ThreadPoolExecutor(1, 1,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());
            }
        }
        return threadPool;
    }

}
