/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailInfoThreadPool 
 * @package com.asura.framework.message.mail.mbean 
 *
 * @date 2015/4/7 14:44 
 */
package com.asura.framework.message.mail.mbean;

import com.asura.framework.base.context.ApplicationContext;
import com.asura.framework.message.mail.thread.MailThreadPool;
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
@MBean(name = "com.asura:type=MailInfoThreadPool")
public class MailInfoThreadPool implements MailInfoThreadPoolMBean {

    private final String SPRING_MAIL_BEAN_NAME = "mail.threadPool";

    @Override
    public long getThreadKeepAliveTime() {
        MailThreadPool threadPool = (MailThreadPool) ApplicationContext.getContext().getBean(SPRING_MAIL_BEAN_NAME);
        return threadPool.getKeepAliveTime();
    }

    @Override
    public int getCoreThreadPoolSize() {
        MailThreadPool threadPool = (MailThreadPool) ApplicationContext.getContext().getBean(SPRING_MAIL_BEAN_NAME);
        return threadPool.getCorePoolSize();
    }

    @Override
    public int getMaxThreadPoolSize() {
        MailThreadPool threadPool = (MailThreadPool) ApplicationContext.getContext().getBean(SPRING_MAIL_BEAN_NAME);
        return threadPool.getMaximumPoolSize();
    }

    @Override
    public long getCompletedTaskCount() {
        MailThreadPool threadPool = (MailThreadPool) ApplicationContext.getContext().getBean(SPRING_MAIL_BEAN_NAME);
        return threadPool.getCompletedTaskCount();
    }

    @Override
    public int getActiveThreadCount() {
        MailThreadPool threadPool = (MailThreadPool) ApplicationContext.getContext().getBean(SPRING_MAIL_BEAN_NAME);
        return threadPool.getActiveCount();
    }

    @Override
    public int getQueueSize() {
        MailThreadPool threadPool = (MailThreadPool) ApplicationContext.getContext().getBean(SPRING_MAIL_BEAN_NAME);
        return threadPool.getQueueSize();
    }
}
