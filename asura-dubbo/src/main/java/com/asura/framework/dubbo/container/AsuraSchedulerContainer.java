/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file AsuraSchedulerContainer 
 * @package com.asura.framework.dubbo.container 
 *
 * @date 2015/3/26 15:01 
 */
package com.asura.framework.dubbo.container;

import com.alibaba.dubbo.container.Container;
import com.asura.framework.base.exception.BusinessException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> 启动Quartz，在dubbo.properties的dubbo.container项最后加入“scheduler” </P>
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
public class AsuraSchedulerContainer implements Container {

    private Logger logger = LoggerFactory.getLogger(AsuraSchedulerContainer.class);

    /**
     * Scheduler
     */
    private Scheduler scheduler;

    /**
     * 启动QuartzScheduler
     *
     * @throws BusinessException
     */
    public void start() throws BusinessException {
        try {
            SchedulerFactory sf = new StdSchedulerFactory("quartz.properties");
            scheduler = sf.getScheduler();
            scheduler.start();
            logger.info(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Quartz started!");
        } catch (SchedulerException e) {
            logger.error("启动Quartz出错:" + e.getMessage(), e.getCause());
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 停止QuartzScheduler
     *
     * @throws BusinessException
     */
    public void stop() throws BusinessException {
        if (null != scheduler) {
            try {
                scheduler.shutdown();
            } catch (SchedulerException e) {
                logger.error("停止Quartz出错:" + e.getMessage(), e.getCause());
                throw new BusinessException(e.getMessage(), e.getCause());
            }
        }
    }
}
