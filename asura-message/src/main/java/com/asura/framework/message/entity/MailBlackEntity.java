/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailBlackEntity 
 * @package com.asura.framework.message.entity 
 *
 * @date 2015/4/7 10:34 
 */
package com.asura.framework.message.entity;

import com.asura.framework.base.entity.BaseEntity;

import java.sql.Timestamp;

/**
 * <p> 邮件黑名单实体 </P>
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
public class MailBlackEntity extends BaseEntity {

    private static final long serialVersionUID = -7160762334593633301L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 邮箱地址
     */
    private String mailAddr;
    /**
     * 黑名单类型：1bug报警 2广告 3正常业务邮件
     */
    private Integer btype;
    /**
     * 黑名单描述
     */
    private String notes;
    /**
     * 逻辑删除 1删除 0未删除
     */
    private Integer isDelete;
    /**
     * 添加时间
     */
    private Timestamp addDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public Integer getBtype() {
        return btype;
    }

    public void setBtype(Integer btype) {
        this.btype = btype;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
}
