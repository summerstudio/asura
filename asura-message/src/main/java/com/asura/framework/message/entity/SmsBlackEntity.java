/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsBlackEntity 
 * @package com.asura.framework.message.entity 
 *
 * @date 2015/4/7 10:42 
 */
package com.asura.framework.message.entity;

import com.asura.framework.base.entity.BaseEntity;

import java.sql.Timestamp;

/**
 * <p> 短信黑名单 </P>
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
public class SmsBlackEntity extends BaseEntity {

    private static final long serialVersionUID = 5742698379779221469L;

    /**
     * 自增id
     */
    private Integer id;
    /**
     * 手机号
     */
    private String tel;
    /**
     * 黑名单类型：1bug报警 2广告 3正常业务
     */
    private Integer btype;
    /**
     * 描述
     */
    private String notes;
    /**
     * 是否删除 1删除 0未删除
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
