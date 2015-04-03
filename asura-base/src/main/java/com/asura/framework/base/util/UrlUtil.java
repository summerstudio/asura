/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file UrlUtil 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/18 10:49 
 */
package com.asura.framework.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p> URL处理工具类 </P>
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
public class UrlUtil {

    /**
     * 编码URL参数. 提供默认的URT-8编码格式
     *
     * @param params
     *
     * @return
     *
     * @author YRJ
     * @created 2014年12月21日 下午5:26:57
     */
    public static String encodeUrl(final String params) {
        return encodeUrl(params, "UTF-8");
    }

    /**
     * 编码URL参数
     *
     * @param params
     *         待编码的值
     * @param encode
     *         编码格式
     *
     * @return
     */
    public static String encodeUrl(final String params, final String encode) {
        try {
            return URLEncoder.encode(params, encode);
        } catch (final UnsupportedEncodingException e) {
            return encodeUrl(params, "UTF-8");
        }
    }

    private UrlUtil() {
        throw new AssertionError("Uninstantiable class");
    }
}
