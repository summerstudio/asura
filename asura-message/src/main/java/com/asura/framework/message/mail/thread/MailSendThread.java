/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailSendThread 
 * @package com.asura.framework.message.mail.thread 
 *
 * @date 2015/4/7 14:17 
 */
package com.asura.framework.message.mail.thread;

import com.asura.framework.message.entity.MailInfoEntity;
import com.asura.framework.message.mail.service.impl.MailSendServiceImpl;

/**
 * <p> 发送邮件线程 </P>
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
public class MailSendThread implements Runnable {

    private MailSendServiceImpl mailSendServiceImpl;

    private MailInfoEntity entity;

    private boolean add; // true 添加记录   false 修改记录

    public MailSendThread(MailSendServiceImpl mailSendServiceImpl, MailInfoEntity entity, boolean add) {
        this.entity = entity;
        this.mailSendServiceImpl = mailSendServiceImpl;
        this.add = add;
    }

    @Override
    public void run() {
        mailSendServiceImpl.sendMail(entity, add);
    }
}
