/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file InitApplicationSide 
 * @package com.asura.framework.monitor.springinit 
 *
 * @date 2015/3/31 11:52 
 */
package com.asura.framework.monitor.springinit;

import com.asura.framework.monitor.annotation.MBeanAnnotationProcessor;
import com.asura.framework.monitor.jmx.server.JmxServer;
import com.asura.framework.monitor.zkclient.ApplicationZkClient;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p> 应用端（即被监控方）初始化 </P>
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
public final class InitApplicationSide implements InitializingBean {

    private InitApplicationSide() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationZkClient.getInstance();
        JmxServer.getInstance();
        //		RemoteFileServer.getInstance();
        MBeanAnnotationProcessor.getInstance();
    }
}
