/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsInfoEntity 
 * @package com.asura.framework.message.entity 
 *
 * @date 2015/4/7 10:44 
 */
package com.asura.framework.message.entity;

import com.asura.framework.base.entity.BaseEntity;

import java.sql.Timestamp;

/**
 * <p> 短信实体 </P>
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
public class SmsInfoEntity extends BaseEntity {

    private static final long serialVersionUID = -7553943313501124836L;

    /**
     * 自增id
     */
    private Integer id;
    /**
     * 手机号
     */
    private String smsTel;
    /**
     * 短信状态:1：失败，2：成功，3：黑名单, 4: 晚上拦截次日早上发送, 5:表示正在队列发送的
     */
    private Integer smsStatus;
    /**
     * 短信内容
     */
    private String smsContent;
    /**
     * 邮件类型：1bug报警 2广告 3正常业务
     */
    private Integer mtype;
    /**
     * 来源:来自那个系统
     */
    private String source;
    /**
     * 重发次数
     */
    private Integer retryNum = Integer.valueOf(0);
    /**
     * 短信接口返回值
     */
    private String returnValue;
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

    public String getSmsTel() {
        return smsTel;
    }

    public void setSmsTel(String smsTel) {
        this.smsTel = smsTel;
    }

    public Integer getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
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

    public Integer getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(Integer retryNum) {
        this.retryNum = retryNum;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
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
