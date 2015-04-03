/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file CommandMBean 
 * @package com.asura.framework.monitor.jmx.mbean 
 *
 * @date 2015/3/31 10:59 
 */
package com.asura.framework.monitor.jmx.mbean;

/**
 * <p> 命令接口 </P>
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
public interface CommandMBean {

    public final static String COMMAND_MXBEAN_NAME = "com.asura:type=Command";

    /**
     * 启动应用
     *
     * @param path
     *
     * @return
     */
    public boolean startApplication(String path);

    /**
     * 停止应用
     *
     * @param path
     *
     * @return
     */
    public boolean stopApplication(String path);
}
