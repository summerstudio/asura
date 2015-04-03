/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SessionConstant 
 * @package com.asura.framework.web.oauth.session 
 *
 * @date 2015/4/1 10:11 
 */
package com.asura.framework.web.oauth.session;

import com.asura.framework.base.util.EncryptionUtil;

/**
 * <p> session值存在redis中的fieldkey </P>
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
public class SessionConstant {

    private static final String COKKIE_KEY = "asuraSession";

    private static final String USER_INFO_FIELDKEY_SUFFIX = "_asura_user_info";

    private static final String USER_REFERER_FIELDKEY_SUFFIX = "_asura_referer_url";

    public static String getUserFieldKey(String sessionId) {
        return sessionId + USER_INFO_FIELDKEY_SUFFIX;
    }

    public static String getRefererFieldKey(String sessionId) {
        return sessionId + USER_REFERER_FIELDKEY_SUFFIX;
    }

    public static String getCookieKey() {
        return EncryptionUtil.encrypt(COKKIE_KEY, EncryptionUtil.ALGORITHM_MD5, EncryptionUtil.ENCODING);
    }
}
