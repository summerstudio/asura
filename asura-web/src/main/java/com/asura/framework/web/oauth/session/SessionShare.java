/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SessionShare 
 * @package com.asura.framework.web.oauth.session 
 *
 * @date 2015/4/1 10:24 
 */
package com.asura.framework.web.oauth.session;

import com.asura.framework.cache.redisannotate.Cache;
import com.asura.framework.cache.redisannotate.CacheDel;
import com.asura.framework.cache.redisannotate.DataStructure;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> session共享 </P>
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
@Component("sessionShare")
public class SessionShare {

    private static final String DOMAIN = ".lvye.com";

    @Cache(dataStructure = DataStructure.hash, key = "asuraSession", fieldKey = "#fieldKey", expireTime = 9999, selfControl = true, isUpdate = true)
    public User saveUserInfo(final String fieldKey, final User user) {
        return user;
    }

    /**
     * 查询用户信息
     *
     * @param fieldKey
     *
     * @return
     */
    @Cache(dataStructure = DataStructure.hash, key = "asuraSession", fieldKey = "#fieldKey", expireTime = 9999, selfControl = true)
    public User findUserInfo(final String fieldKey) {
        return null;
    }

    /**
     * 保存referer缓存
     *
     * @param fieldKey
     *         SessionFieldKeys.getRefererFieldKey
     *
     * @return
     */
    @Cache(dataStructure = DataStructure.hash, key = "asuraSession", fieldKey = "#fieldKey", expireTime = 9999, selfControl = true, isUpdate = true)
    public Referer saveReferer(String fieldKey, StringBuffer referer_url, String queryString) {
        if (!"".equals(queryString)) {
            referer_url.append("?").append(queryString);
        }
        Referer r = new Referer();
        r.setRefererUrl(referer_url.toString());
        return r;
    }

    /**
     * 查询referer缓存
     *
     * @param fieldKey
     *         SessionFieldKeys.getRefererFieldKey
     *
     * @return
     */
    @Cache(dataStructure = DataStructure.hash, key = "asuraSession", fieldKey = "#fieldKey", expireTime = 9999, selfControl = true)
    public Referer findReferer(final String fieldKey) {
        return null;
    }

    @CacheDel(key = "asuraSession", fieldKey = "#fieldKey", selfControl = true)
    public String destroy(String fieldKey) {
        return fieldKey;
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param key
     *         = asuraSessionId
     * @param value
     *         = sessionId
     */
    public void saveCookie(final HttpServletResponse response, final String key, final String value) {
        final Cookie cookie = new Cookie(key, value);
        cookie.setDomain(DOMAIN);
        cookie.setMaxAge(604800);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取 asurasessionId
     *
     * @param request
     *
     * @return
     */
    public String getAsuraSessionId(HttpServletRequest request) {
        String asuraSessionId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (SessionConstant.getCookieKey().equals(ck.getName())) {
                    asuraSessionId = ck.getValue();
                }
            }
        }
        return asuraSessionId;
    }
}
