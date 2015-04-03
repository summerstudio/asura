/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file AsuraJob 
 * @package com.asura.framework.quartz.job 
 *
 * @date 2015/3/25 10:17 
 */
package com.asura.framework.quartz.job;

import com.asura.framework.conf.subscribe.ConfigSubscriber;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * <p> 有状态定时任务基类 </P>
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
public abstract class AsuraJob implements StatefulJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (quartzKP()) {
            System.out.println(jobExecutionContext.getJobDetail().getJobClass().getName() + " 定时任务空跑.....");
        } else {
            run(jobExecutionContext);
        }
    }

    public abstract void run(JobExecutionContext jobExecutionContext);

    /**
     * 是否空跑定时任务
     *
     * @return true 空跑   ； false 不空跑
     */
    private boolean quartzKP() {
        String type = EnumSysConfig.asura_quartzKP.getType();
        String code = EnumSysConfig.asura_quartzKP.getCode();
        String value = ConfigSubscriber.getInstance().getConfigValue(type, code);
        if (value == null) {
            value = EnumSysConfig.asura_quartzKP.getDefaultValue();
        }
        if ("on".equals(value)) { // 空跑
            return true;
        } else {
            return false;
        }
    }
}
