/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file User 
 * @package com.asura.framework.web.oauth.session 
 *
 * @date 2015/4/1 10:07 
 */
package com.asura.framework.web.oauth.session;

import com.asura.framework.base.entity.BaseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> session对象信息 </P>
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
public class User extends BaseEntity {

    private static final long serialVersionUID = -9021627271641153705L;

    private Integer uid;
    private String username;
    private String phone;
    private String email;

    /**
     * @return the uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     *         the uid to set
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *         the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *         the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *         the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public static User getLoginUser(HttpServletRequest request) {
        if (request == null)
            return null;
        return (User) request.getAttribute("user");
    }
}
