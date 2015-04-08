/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsSendThread 
 * @package com.asura.framework.message.sms.thread 
 *
 * @date 2015/4/7 15:45 
 */
package com.asura.framework.message.sms.thread;

import com.asura.framework.message.entity.SmsInfoEntity;
import com.asura.framework.message.sms.service.impl.SmsSendServiceImpl;

/**
 * <p> 短信服务线程 </P>
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
public class SmsSendThread implements Runnable {

    private SmsSendServiceImpl smsSendServiceImpl;

    private SmsInfoEntity entity;

    private boolean add; // true 增加   false 修改

    public SmsSendThread(SmsSendServiceImpl smsSendServiceImpl, SmsInfoEntity entity, boolean add) {
        this.entity = entity;
        this.smsSendServiceImpl = smsSendServiceImpl;
        this.add = add;
    }

    @Override
    public void run() {
        smsSendServiceImpl.sendSms(entity, add);
    }
}
