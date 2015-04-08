/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailInfoVO 
 * @package com.asura.framework.message.mail.entity 
 *
 * @date 2015/4/7 11:11 
 */
package com.asura.framework.message.mail.entity;

import com.asura.framework.base.entity.BaseEntity;

/**
 * <p> 邮件业务对象 </P>
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
public class MailInfoVO extends BaseEntity {

    private static final long serialVersionUID = 2049230029149340075L;

    /**
     * 类型 1bug报警 2广告 3正常业务邮件
     */
    private Integer mtype;
    /**
     * 来自那个系统
     */
    private String source;
    /**
     * 邮件地址多个|分开
     */
    private String mailAddrs;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 时间戳
     */
    private Integer timestamp;
    /**
     * 接口签名
     */
    private String sign;

    /**
     * @return the mtype
     */
    public Integer getMtype() {
        return mtype;
    }

    /**
     * @param mtype
     *         the mtype to set
     */
    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    /**
     * @return the mailAddr
     */
    public String getMailAddrs() {
        return mailAddrs;
    }

    /**
     * @param mailAddrs
     *         the mailAddr to set
     */
    public void setMailAddrs(String mailAddrs) {
        this.mailAddrs = mailAddrs;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *         the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *         the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     *         the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the timestamp
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *         the timestamp to set
     */
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign
     *         the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }
}
