/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailInfoEntity 
 * @package com.asura.framework.message.entity 
 *
 * @date 2015/4/7 10:38 
 */
package com.asura.framework.message.entity;

import com.asura.framework.base.entity.BaseEntity;

import java.sql.Timestamp;

/**
 * <p> 邮件实体 </P>
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
public class MailInfoEntity extends BaseEntity {

    private static final long serialVersionUID = -3994701550062293972L;

    /**
     * 主键，自增
     */
    private Integer id;
    /**
     * 邮件类型：1bug报警 2广告 3正常业务邮件
     */
    private Integer mtype;
    /**
     * 邮件来源：来自那个系统
     */
    private String source;
    /**
     * 收件人
     */
    private String mailAddr;
    /**
     * 主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 邮件状态 1：失败；2：成功；3：黑名单
     */
    private Integer mailStatus;
    /**
     * 重发次数
     */
    private Integer retryNum = Integer.valueOf(0);
    /**
     * 热删除标记（0：未删除；1：已删除）
     */
    private Integer isDelete;
    /**
     * 添加时间
     */
    private Timestamp addDate;
    /**
     * 发送时间
     */
    private Timestamp sendDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(Integer mailStatus) {
        this.mailStatus = mailStatus;
    }

    public Integer getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(Integer retryNum) {
        this.retryNum = retryNum;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }
}
