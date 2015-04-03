/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SignatureUtil 
 * @package com.asura.framework.web.oauth.util 
 *
 * @date 2015/4/1 10:05 
 */
package com.asura.framework.web.oauth.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.*;

/**
 * <p> 请求授权签名工具 </P>
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
public class SignatureUtil {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    /**
     * Computes RFC 2104-compliant HMAC signature.
     *
     * @param data
     *         The data to be signed.
     * @param key
     *         The signing key.
     *
     * @return The Base64-encoded RFC 2104-compliant HMAC signature.
     *
     * @throws java.security.SignatureException
     *         when signature generation fails
     */
    public static String calculateRFC2104HMAC(String data, String key) throws SignatureException {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = Base64.encodeBase64String(rawHmac);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    public static String genMacParams(Map<String, Object> map) throws UnsupportedEncodingException {
        Set<String> set = map.keySet();
        String[] arrs = new String[set.size()];
        set.toArray(arrs);
        Arrays.sort(arrs);
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : arrs) {
            stringBuffer.append(getUnicode(s) + "=" + getUnicode(map.get(s).toString()));
        }
        return stringBuffer.toString();
    }

    public static String genOauthHeader(Map<String, Object> map) throws UnsupportedEncodingException {
        Set<String> set = map.keySet();
        List<String> arrs = new ArrayList<>(set.size());
        for (String s : set) {
            arrs.add(getUnicode(s) + "=" + getUnicode(map.get(s).toString()));
        }
        return "MAC " + StringUtils.join(arrs, ",");
    }

    /**
     * 字符串转换unicode
     */
    public static String getUnicode(String s, String charset) throws UnsupportedEncodingException {
        byte[] b1 = s.getBytes(charset);
        return new String(b1, charset);

    }

    public static String getUnicode(String s) throws UnsupportedEncodingException {
        return getUnicode(s, "UTF-8");
    }
}
