/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailSendServiceImpl 
 * @package com.asura.framework.message.mail.service.impl 
 *
 * @date 2015/4/7 14:28 
 */
package com.asura.framework.message.mail.service.impl;

import com.asura.framework.logback.ErrorLog;
import com.asura.framework.message.entity.MailBlackEntity;
import com.asura.framework.message.entity.MailInfoEntity;
import com.asura.framework.message.mail.constant.EnumMailType;
import com.asura.framework.message.mail.dao.MailBlackDao;
import com.asura.framework.message.mail.dao.MailDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * <p> 邮件发送服务实现 </P>
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
@Service("mail.mailSendServiceImpl")
public class MailSendServiceImpl {

    private static final Logger errorLogger = LoggerFactory.getLogger(ErrorLog.class);

    @Resource(name = "mail.mailDao")
    private MailDao mailDao;

    @Resource(name = "mail.mailBlackDao")
    private MailBlackDao mailBlackDao;

    @Resource(name = "bugMailSender")
    private JavaMailSender bugMailSender;

    @Resource(name = "adMailSender")
    private JavaMailSender adMailSender;

    @Resource(name = "bizMailSender")
    private JavaMailSender bizMailSender;

    @Value("#{'${bug.mail.username}'}")
    String bug_username;

    @Value("#{'${ad.mail.username}'}")
    String ad_username;

    @Value("#{'${biz.mail.username}'}")
    String biz_username;

    @Value("#{'${bug.mail.fromname}'}")
    String bug_fromname;

    @Value("#{'${ad.mail.fromname}'}")
    String ad_fromname;

    @Value("#{'${biz.mail.fromname}'}")
    String biz_fromname;

    /**
     * 发送邮件
     *
     * @param entity
     * @param add
     *
     * @throws Throwable
     */
    public synchronized void sendMail(MailInfoEntity entity, boolean add) {
        if (EnumMailType.bug.getCode().intValue() == entity.getMtype().intValue()) {
            doSend(bugMailSender, bug_username, bug_fromname, entity, add);
        } else if (EnumMailType.ad.getCode().intValue() == entity.getMtype().intValue()) {
            doSend(adMailSender, ad_username, ad_fromname, entity, add);
        } else if (EnumMailType.biz.getCode().intValue() == entity.getMtype().intValue()) {
            doSend(bizMailSender, biz_username, biz_fromname, entity, add);
        }

    }

    private void doSend(JavaMailSender mailSender, String username, String fromname, MailInfoEntity entity, boolean add) {
        try {
            if (isMailBlack(entity.getMailAddr(), entity.getMtype())) {
                entity.setMailStatus(3); // 黑名单用户
            } else {
                MimeMessage mineMessage = mailSender.createMimeMessage();
                //设置发送邮件的编码格式
                MimeMessageHelper messageHelper = new MimeMessageHelper(mineMessage, true, "utf-8");
                //设置发送邮箱，及其发送邮箱的显示名称
                messageHelper.setFrom(username, fromname);
                messageHelper.setSubject(entity.getSubject());
                //邮件内容的格式根据传入的格式参数来判断
                messageHelper.setText(entity.getContent(), true);
                messageHelper.setTo(entity.getMailAddr());
                mailSender.send(mineMessage);
                entity.setMailStatus(2);
            }
        } catch (MailException e) {
            entity.setMailStatus(1); // 发送失败
            errorLogger.error(add + "<<-- add, 邮件发送异常：" + entity.toJsonStr(), e);
        } catch (UnsupportedEncodingException e) {
            entity.setMailStatus(1); // 发送失败
            errorLogger.error(add + "<<-- add, 邮件发送异常：" + entity.toJsonStr(), e);
        } catch (Exception e) {
            entity.setMailStatus(1); // 发送失败
            errorLogger.error(add + "<<-- add, 邮件发送异常：" + entity.toJsonStr(), e);
        } finally {
            if (add) {
                entity.setSendDate(new Timestamp(System.currentTimeMillis()));
                mailDao.saveMailInfoEntity(entity);
            } else {
                entity.setSendDate(new Timestamp(System.currentTimeMillis()));
                entity.setRetryNum(entity.getRetryNum().intValue() + 1);
                mailDao.updateMailInfoEntity(entity);
            }

        }
    }

    /**
     * 验证该邮箱是否在黑名单
     *
     * @param mailAddr
     *
     * @return
     */
    public boolean isMailBlack(String mailAddr, Integer btype) {
        MailBlackEntity mbe = mailBlackDao.getMailBlackByMailAddr(mailAddr, btype);
        if (mbe != null) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        Properties p = new Properties();
        //设置邮件发送服务器的地址
        p.setProperty("mail.smtp.host", "192.168.0.149");
        //设置使用权限验证
        p.setProperty("mail.smtp.auth", "false");
        //设置用户身份验证凭据
        Session ses = Session.getDefaultInstance(p);
        ses.setDebug(true);//设置是否出现回显信息

        try {
            ses.getTransport().connect("zhangshaobin@lvye.com.cn", "zhang232621!Q");
            p.clear();
            System.out.println("连接成功.....");
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
