/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file Referer 
 * @package com.asura.framework.web.oauth.session 
 *
 * @date 2015/4/1 10:09 
 */
package com.asura.framework.web.oauth.session;

import com.asura.framework.base.entity.BaseEntity;

/**
 * <p> 跳转URL实体 </P>
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
public class Referer extends BaseEntity {

    private static final long serialVersionUID = 2200523228947696978L;

    private String refererUrl;

    /**
     * @return the refererUrl
     */
    public String getRefererUrl() {
        return refererUrl;
    }

    /**
     * @param refererUrl
     *         the refererUrl to set
     */
    public void setRefererUrl(String refererUrl) {
        this.refererUrl = refererUrl;
    }
}
