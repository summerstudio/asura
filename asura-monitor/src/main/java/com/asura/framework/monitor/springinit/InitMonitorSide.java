/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file InitMonitorSide 
 * @package com.asura.framework.monitor.springinit 
 *
 * @date 2015/3/31 11:56 
 */
package com.asura.framework.monitor.springinit;

import com.asura.framework.monitor.annotation.MBeanAnnotationProcessor;
import com.asura.framework.monitor.zkclient.MonitorZkClient;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p> 监控方启动 </P>
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
public final class InitMonitorSide implements InitializingBean {

    private InitMonitorSide() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MonitorZkClient.getInstance();
        MBeanAnnotationProcessor.getInstance();
    }
}
