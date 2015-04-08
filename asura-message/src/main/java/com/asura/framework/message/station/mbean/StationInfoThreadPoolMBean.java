/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationInfoThreadPoolMBean 
 * @package com.asura.framework.message.station.mbean 
 *
 * @date 2015/4/7 16:51 
 */
package com.asura.framework.message.station.mbean;

/**
 * <p> jmx 线程池信息接口 </P>
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
public interface StationInfoThreadPoolMBean {

    /**
     * 线程保持活动时间
     *
     * @return
     */
    public long getThreadKeepAliveTime();

    /**
     * 核心线程数量
     *
     * @return
     */
    public int getCoreThreadPoolSize();

    /**
     * 获取最大线程数
     *
     * @return
     */
    public int getMaxThreadPoolSize();

    /**
     * 完成任务数
     *
     * @return
     */
    public long getCompletedTaskCount();

    /**
     * 活动线程数
     *
     * @return
     */
    public int getActiveThreadCount();

    /**
     * 队列缓冲数
     *
     * @return
     */
    public int getQueueSize();
}
