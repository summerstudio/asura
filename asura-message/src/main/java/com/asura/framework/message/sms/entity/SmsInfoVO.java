/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsInfoVO 
 * @package com.asura.framework.message.sms.entity 
 *
 * @date 2015/4/7 15:04 
 */
package com.asura.framework.message.sms.entity;

import com.asura.framework.base.entity.BaseEntity;

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
public class SmsInfoVO extends BaseEntity {

    private static final long serialVersionUID = -7235815850047885016L;

    /**
     * 手机号 多个|分开
     */
    private String smsTel;

    /**
     * 短信内容
     */
    private String smsContent;

    /**
     * 类型
     */
    private Integer mtype;

    /**
     * 来源
     */
    private String source;

    /**
     * 时间戳
     */
    private Integer timestamp;

    /**
     * 接口签名
     */
    private String sign;

    /**
     * @return the smsTel
     */
    public String getSmsTel() {
        return smsTel;
    }

    /**
     * @param smsTel
     *         the smsTel to set
     */
    public void setSmsTel(String smsTel) {
        this.smsTel = smsTel;
    }

    /**
     * @return the smsContent
     */
    public String getSmsContent() {
        return smsContent;
    }

    /**
     * @param smsContent
     *         the smsContent to set
     */
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

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
