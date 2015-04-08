/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsThreadPool 
 * @package com.asura.framework.message.sms.thread 
 *
 * @date 2015/4/7 15:43 
 */
package com.asura.framework.message.sms.thread;

import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p> 短信服务线程池 </P>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author SZL
 * @version 1.0
 * @since 1.0
 */
@Service("sms.smsThreadpool")
public class SmsThreadPool {

    /**
     * 线程池
     */
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 100, 3000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    /**
     * 获取线程保持活动的时间
     *
     * @return
     */
    public long getKeepAliveTime() {
        return threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS);
    }

    /**
     * 设置线程保持活动的时间
     *
     * @param keepAliveTime
     */
    public void setKeepAliveTime(long keepAliveTime) {
        threadPoolExecutor.setKeepAliveTime(keepAliveTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取核心线程数量
     *
     * @return
     */
    public int getCorePoolSize() {
        return threadPoolExecutor.getCorePoolSize();
    }

    /**
     * 设置核心线程数量
     *
     * @param corePoolSize
     */
    public void setCorePoolSize(int corePoolSize) {
        threadPoolExecutor.setCorePoolSize(corePoolSize);
    }

    /**
     * 获取最大线程数
     *
     * @return
     */
    public int getMaximumPoolSize() {
        return threadPoolExecutor.getMaximumPoolSize();
    }

    /**
     * 设置最大线程数
     *
     * @param maximumPoolSize
     */
    public void setMaximumPoolSize(int maximumPoolSize) {
        threadPoolExecutor.setMaximumPoolSize(maximumPoolSize);
    }

    /**
     * 获取完成的任务数
     *
     * @return
     */
    public long getCompletedTaskCount() {
        return threadPoolExecutor.getCompletedTaskCount();
    }

    /**
     * 获取活动线程数
     *
     * @return
     */
    public int getActiveCount() {
        return threadPoolExecutor.getActiveCount();
    }

    public int getQueueSize() {
        return this.threadPoolExecutor.getQueue().size();
    }

    /**
     * 执行线程 (异步执行)
     *
     * @param command
     */
    public void execute(Runnable command) {
        threadPoolExecutor.execute(command);
    }

    /**
     * 线程池停止,池中的任务完成后停止
     */
    public void shutdown() {
        threadPoolExecutor.shutdown();
    }
}
