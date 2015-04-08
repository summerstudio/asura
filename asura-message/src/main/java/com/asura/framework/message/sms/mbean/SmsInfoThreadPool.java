/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsInfoThreadPool 
 * @package com.asura.framework.message.sms.mbean 
 *
 * @date 2015/4/7 16:15 
 */
package com.asura.framework.message.sms.mbean;

import com.asura.framework.base.context.ApplicationContext;
import com.asura.framework.message.sms.thread.SmsThreadPool;
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
@MBean(name = "com.asura:type=SmsInfoThreadPool")
public class SmsInfoThreadPool implements SmsInfoThreadPoolMBean {

    private final String SPRING_SMS_BEAN_NAME = "sms.smsThreadpool";

    @Override
    public long getThreadKeepAliveTime() {
        SmsThreadPool threadPool = (SmsThreadPool) ApplicationContext.getContext().getBean(SPRING_SMS_BEAN_NAME);
        return threadPool.getKeepAliveTime();
    }

    @Override
    public int getCoreThreadPoolSize() {
        SmsThreadPool threadPool = (SmsThreadPool) ApplicationContext.getContext().getBean(SPRING_SMS_BEAN_NAME);
        return threadPool.getCorePoolSize();
    }

    @Override
    public int getMaxThreadPoolSize() {
        SmsThreadPool threadPool = (SmsThreadPool) ApplicationContext.getContext().getBean(SPRING_SMS_BEAN_NAME);
        return threadPool.getMaximumPoolSize();
    }

    @Override
    public long getCompletedTaskCount() {
        SmsThreadPool threadPool = (SmsThreadPool) ApplicationContext.getContext().getBean(SPRING_SMS_BEAN_NAME);
        return threadPool.getCompletedTaskCount();
    }

    @Override
    public int getActiveThreadCount() {
        SmsThreadPool threadPool = (SmsThreadPool) ApplicationContext.getContext().getBean(SPRING_SMS_BEAN_NAME);
        return threadPool.getActiveCount();
    }

    @Override
    public int getQueueSize() {
        SmsThreadPool threadPool = (SmsThreadPool) ApplicationContext.getContext().getBean(SPRING_SMS_BEAN_NAME);
        return threadPool.getQueueSize();
    }
}
