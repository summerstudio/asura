/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SystemInfo 
 * @package com.asura.framework.monitor.jmx.mbean 
 *
 * @date 2015/3/31 11:00 
 */
package com.asura.framework.monitor.jmx.mbean;

import com.asura.framework.monitor.annotation.MBean;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * <p> 系统信息实现类 </P>
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
@MBean(name = "com.asura:type=SystemInfo")
public class SystemInfo implements SystemInfoMBean {

    /**
     * 获取操作系统MBean
     *
     * @return
     */
    private OperatingSystemMXBean getOperatingSystemMXBean() {
        return (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    /* (non-Javadoc)
     * @see com.sfbest.monitor.mbean.SystemInfoMBean#getTotalPhysicalMemorySize()
     */
    @Override
    public long getTotalPhysicalMemorySize() {
        return getOperatingSystemMXBean().getTotalPhysicalMemorySize();
    }

    /* (non-Javadoc)
     * @see com.sfbest.monitor.mbean.SystemInfoMBean#getFreePhysicalMemorySize()
     */
    @Override
    public long getFreePhysicalMemorySize() {
        return getOperatingSystemMXBean().getFreePhysicalMemorySize();
    }

    /* (non-Javadoc)
     * @see com.sfbest.monitor.mbean.SystemInfoMBean#getMaxJvmMemorySize()
     */
    @Override
    public long getMaxJvmMemorySize() {
        return Runtime.getRuntime().maxMemory();
    }

    /* (non-Javadoc)
     * @see com.sfbest.monitor.mbean.SystemInfoMBean#getTotalJvmMemorySize()
     */
    @Override
    public long getTotalJvmMemorySize() {
        return Runtime.getRuntime().totalMemory();
    }

    /* (non-Javadoc)
     * @see com.sfbest.monitor.mbean.SystemInfoMBean#getFreeJvmMemorySize()
     */
    @Override
    public long getFreeJvmMemorySize() {
        return Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getJvmStartTime() {
        return ManagementFactory.getRuntimeMXBean().getStartTime();
    }

    @Override
    public String getJvmVersion() {
        return ManagementFactory.getRuntimeMXBean().getVmVersion();
    }

    @Override
    public String getJvmName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    @Override
    public long getTotalLoadedClassCount() {
        return ManagementFactory.getClassLoadingMXBean().getTotalLoadedClassCount();
    }

    @Override
    public long getLoadedClassCount() {
        return ManagementFactory.getClassLoadingMXBean().getLoadedClassCount();
    }

    @Override
    public long getHeapMemoryInit() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getInit();
    }

    @Override
    public long getHeapMemoryUsed() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
    }

    @Override
    public long getHeapMemoryMax() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
    }

    @Override
    public long getThreadCount() {
        return ManagementFactory.getThreadMXBean().getThreadCount();
    }

    @Override
    public long getPeakThreadCount() {
        return ManagementFactory.getThreadMXBean().getPeakThreadCount();
    }

    @Override
    public long getDaemonThreadCount() {
        return ManagementFactory.getThreadMXBean().getDaemonThreadCount();
    }

    @Override
    public long getTotalStartedThreadCount() {
        return ManagementFactory.getThreadMXBean().getTotalStartedThreadCount();
    }
}
