/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file CallAccounts 
 * @package com.asura.framework.web.oauth.accounts 
 *
 * @date 2015/4/1 11:33 
 */
package com.asura.framework.web.oauth.accounts;

import com.asura.framework.base.exception.BusinessException;
import com.asura.framework.base.util.Check;
import com.asura.framework.base.util.JsonEntityTransform;
import com.asura.framework.base.util.RandomUtil;
import com.asura.framework.web.oauth.session.User;
import com.asura.framework.web.oauth.util.SignatureUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 访问用户中心 </P>
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
public class CallAccounts {

    /**
     * 获取用户信息
     *
     * @param code
     * @param appid
     * @param appkey
     * @param postUrl
     *
     * @return
     */
    public static User getUserByAccounts(final String code, final String appid, final String appkey, final String postUrl) {
        String req = "";
        User user = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            params.put("ts", new Date().getTime());
            params.put("client_id", appid);
            params.put("nonce", Integer.valueOf(RandomUtil.genRandomNum(9)));
            String macParams = SignatureUtil.genMacParams(params);
            String result = SignatureUtil.calculateRFC2104HMAC(macParams, appkey);
            params.put("mac", result);
            String oauthHeader = SignatureUtil.genOauthHeader(params);
            req = postHttpRequest(oauthHeader, postUrl);
            user = JsonEntityTransform.json2Entity(req, User.class);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new BusinessException("访问accounts站点获取用户信息异常", e);
        } catch (BusinessException e) {
            e.printStackTrace();
            throw new BusinessException("访问accounts站点获取用户信息异常", e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BusinessException("访问accounts站点获取用户信息异常", e);
        } catch (SignatureException e) {
            e.printStackTrace();
            throw new BusinessException("访问accounts站点获取用户信息异常", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("访问accounts站点获取用户信息异常", e);
        }
        if (Check.NuNObj(user)) {
            throw new BusinessException("访问accounts站点异常，返回信息转换成user对象后为空值，返回信息：" + req);
        }
        return user;
    }

    /**
     * 访问accounts站点获取用户信息
     *
     * @param oauthHeader
     * @param postUrl
     *
     * @return
     *
     * @throws Exception
     */
    private static String postHttpRequest(final String oauthHeader, final String postUrl) throws IOException {
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(postUrl);
            connection = (HttpURLConnection) url.openConnection();
            //connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setRequestProperty("Authorization", oauthHeader);
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str = "";
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}
