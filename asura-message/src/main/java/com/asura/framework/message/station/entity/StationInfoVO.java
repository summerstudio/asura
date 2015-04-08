/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationInfoVO 
 * @package com.asura.framework.message.station.entity 
 *
 * @date 2015/4/7 16:24 
 */
package com.asura.framework.message.station.entity;

import com.asura.framework.base.entity.BaseEntity;

/**
 * <p> 站内信实体 </P>
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
public class StationInfoVO extends BaseEntity {

    private static final long serialVersionUID = -680626199916370721L;

    private Integer id;

    /**
     * 发送者
     */
    private String uid;
    /**
     * 接受者 多个|分开
     */
    private String touid;
    /**
     * 内容
     */
    private String msg;
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
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *         the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the touid
     */
    public String getTouid() {
        return touid;
    }

    /**
     * @param touid
     *         the touid to set
     */
    public void setTouid(String touid) {
        this.touid = touid;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *         the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
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

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *         the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
