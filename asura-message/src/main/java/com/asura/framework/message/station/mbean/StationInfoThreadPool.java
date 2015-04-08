/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationInfoThreadPool 
 * @package com.asura.framework.message.station.mbean 
 *
 * @date 2015/4/7 16:52 
 */
package com.asura.framework.message.station.mbean;

import com.asura.framework.base.context.ApplicationContext;
import com.asura.framework.message.station.thread.StationThreadPool;
import com.asura.framework.monitor.annotation.MBean;

/**
 * <p> jmx 线程池信息接口实现 </P>
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
@MBean(name = "com.asura:type=StationInfoThreadPool")
public class StationInfoThreadPool implements StationInfoThreadPoolMBean {

    private final String SPRING_STATION_BEAN_NAME = "station.stationThreadPool";

    @Override
    public long getThreadKeepAliveTime() {
        StationThreadPool threadPool = (StationThreadPool) ApplicationContext.getContext().getBean(SPRING_STATION_BEAN_NAME);
        return threadPool.getKeepAliveTime();
    }

    @Override
    public int getCoreThreadPoolSize() {
        StationThreadPool threadPool = (StationThreadPool) ApplicationContext.getContext().getBean(SPRING_STATION_BEAN_NAME);
        return threadPool.getCorePoolSize();
    }

    @Override
    public int getMaxThreadPoolSize() {
        StationThreadPool threadPool = (StationThreadPool) ApplicationContext.getContext().getBean(SPRING_STATION_BEAN_NAME);
        return threadPool.getMaximumPoolSize();
    }

    @Override
    public long getCompletedTaskCount() {
        StationThreadPool threadPool = (StationThreadPool) ApplicationContext.getContext().getBean(SPRING_STATION_BEAN_NAME);
        return threadPool.getCompletedTaskCount();
    }

    @Override
    public int getActiveThreadCount() {
        StationThreadPool threadPool = (StationThreadPool) ApplicationContext.getContext().getBean(SPRING_STATION_BEAN_NAME);
        return threadPool.getActiveCount();
    }

    @Override
    public int getQueueSize() {
        StationThreadPool threadPool = (StationThreadPool) ApplicationContext.getContext().getBean(SPRING_STATION_BEAN_NAME);
        return threadPool.getQueueSize();
    }
}
