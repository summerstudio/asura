/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SystemInfoMBean 
 * @package com.asura.framework.monitor.jmx.mbean 
 *
 * @date 2015/3/31 10:57 
 */
package com.asura.framework.monitor.jmx.mbean;

/**
 * <p> 系统信息 </P>
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
public interface SystemInfoMBean {

    public final static String SYSTEMINFO_MXBEAN_NAME = "com.asura:type=SystemInfo";

    /**
     * 操作系统物理内存总数
     *
     * @return
     */
    public long getTotalPhysicalMemorySize();

    /**
     * 操作系统空闲物理内存总数
     *
     * @return
     */
    public long getFreePhysicalMemorySize();

    /**
     * JVM最大能识别的内存数
     *
     * @return
     */
    public long getMaxJvmMemorySize();

    /**
     * JVM总内存数
     *
     * @return
     */
    public long getTotalJvmMemorySize();

    /**
     * JVM空闲内存数
     *
     * @return
     */
    public long getFreeJvmMemorySize();

    /**
     * JVM启动时间
     *
     * @return
     */
    public long getJvmStartTime();

    /**
     * JVM版本
     *
     * @return
     */
    public String getJvmVersion();

    /**
     * JVM实现名称
     *
     * @return
     */
    public String getJvmName();

    /**
     * JVM启动后加载的Class总数
     *
     * @return
     */
    public long getTotalLoadedClassCount();

    /**
     * JVM当前加载的Class数
     *
     * @return
     */
    public long getLoadedClassCount();

    /**
     * JVM初始堆大小
     *
     * @return
     */
    public long getHeapMemoryInit();

    /**
     * JVM堆使用大小
     *
     * @return
     */
    public long getHeapMemoryUsed();

    /**
     * JVM支持的最大堆内存
     *
     * @return
     */
    public long getHeapMemoryMax();

    /**
     * 当前有效线程数，包括守护线程和非守护线程
     *
     * @return
     */
    public long getThreadCount();

    /**
     * 自JVM启动或重置后活动线程的峰值
     *
     * @return
     */
    public long getPeakThreadCount();

    /**
     * 守护线程数
     *
     * @return
     */
    public long getDaemonThreadCount();

    /**
     * 自JVM启动后创建的线程总数
     *
     * @return
     */
    public long getTotalStartedThreadCount();
}
