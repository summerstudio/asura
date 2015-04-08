/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationInfoEntity 
 * @package com.asura.framework.message.entity 
 *
 * @date 2015/4/7 10:48 
 */
package com.asura.framework.message.entity;

import com.asura.framework.base.entity.BaseEntity;

import java.sql.Timestamp;

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
public class StationInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1673337256942205902L;

    /**
     * 自增id
     */
    private Integer id;
    /**
     * 发送人uid
     */
    private Integer uid;
    /**
     * 接收人uid
     */
    private Integer touid;
    /**
     * 内容
     */
    private String msg;
    /**
     * 来源：来自那个系统
     */
    private String source;
    /**
     * 状态：1失败 2成功
     */
    private Integer stationStatus;
    /**
     * 重发次数
     */
    private Integer retryNum = Integer.valueOf(0);
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTouid() {
        return touid;
    }

    public void setTouid(Integer touid) {
        this.touid = touid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(Integer stationStatus) {
        this.stationStatus = stationStatus;
    }

    public Integer getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(Integer retryNum) {
        this.retryNum = retryNum;
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
