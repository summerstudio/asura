/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsSendServiceImpl 
 * @package com.asura.framework.message.sms.service.impl 
 *
 * @date 2015/4/7 15:47 
 */
package com.asura.framework.message.sms.service.impl;

import com.asura.framework.base.exception.BusinessException;
import com.asura.framework.logback.ErrorLog;
import com.asura.framework.message.entity.SmsBlackEntity;
import com.asura.framework.message.entity.SmsInfoEntity;
import com.asura.framework.message.sms.dao.SmsBlackDao;
import com.asura.framework.message.sms.dao.SmsDao;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * <p> 短信服务业务实现 </P>
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
@Service("sms.smsSendServiceImpl")
public class SmsSendServiceImpl {

    private static final Logger errorLogger = LoggerFactory.getLogger(ErrorLog.class);

    @Resource(name = "sms.SmsDao")
    private SmsDao smsDao;

    @Resource(name = "sms.smsBlackDao")
    private SmsBlackDao smsBlackDao;

    @Value("#{'${sms.mw.url}'}")
    private String sms_mw_url;

    @Value("#{'${sms.mw.userId}'}")
    private String sms_mw_userId;

    @Value("#{'${sms.mw.password}'}")
    private String sms_mw_password;

    /**
     * 发送短信
     *
     * @param entity
     */
    public void sendSms(SmsInfoEntity entity, boolean add) {
        try {
            if (isSmsBlack(entity.getSmsTel(), entity.getMtype())) {
                entity.setSmsStatus(3);
            } else {
                boolean isSuccess = mwSendSmsNew(entity);
                if (isSuccess) {
                    entity.setSmsStatus(2);
                } else {
                    entity.setSmsStatus(1);
                }
            }
        } catch (Exception e) {
            entity.setSmsStatus(1);
            errorLogger.error(add + " <<-- true添加， false更新, 短信发送异常：" + entity.toJsonStr(), e);
        } finally {
            if (add) {
                entity.setSendDate(new Timestamp(System.currentTimeMillis()));
                smsDao.saveSmsInfoEntity(entity);
            } else {
                entity.setSendDate(new Timestamp(System.currentTimeMillis()));
                entity.setRetryNum(entity.getRetryNum().intValue() + 1);
                smsDao.updateSmsInfoEntity(entity);
            }
        }
    }

    private boolean mwSendSmsNew(SmsInfoEntity entity) throws BusinessException {
        boolean isSuccess = false;
        String result = null;
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        try {
            // 构造HttpClient的实例
            httpClient = new HttpClient();
            postMethod = new PostMethod(sms_mw_url);
            // 有中文，需要转码
            postMethod.getParams().setContentCharset("utf-8");
            // 填入各个表单域的值
            NameValuePair userId = new NameValuePair("userId", sms_mw_userId);// J01306
            NameValuePair password = new NameValuePair("password", sms_mw_password);// 331002
            NameValuePair pszMobis = new NameValuePair("pszMobis", entity.getSmsTel());// 13552736269
            NameValuePair pszMsg = new NameValuePair("pszMsg", entity.getSmsContent());// 测试短信
            NameValuePair iMobiCount = new NameValuePair("iMobiCount", "1");// 短信都是单发, 群发是有几个手机号这里写几
            NameValuePair pszSubPort0 = new NameValuePair("pszSubPort0", "*");// 默认写法

            postMethod.setRequestBody(new NameValuePair[]{userId, password, pszMobis, pszMsg, iMobiCount, pszSubPort0});
            // 使用系统提供的默认的恢复策略,3次
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

            // 执行postMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (200 == statusCode) {
                // 请求返回的内容 <?xml version="1.0" encoding="utf-8"?><string
                // xmlns="http://tempuri.org/">6028932869336414779</string>
                String response = postMethod.getResponseBodyAsString();
                // 截取请求返回的内容取得我们想要的字段,每一次返回的字段是不同的
                result = response.split("/\">")[1].split("</")[0];
                // 判断result的长度
                int length = result.length();
                if (length > 10) {// 如果短信发送成功返回的字符串长度不小于19
                    isSuccess = true;
                } else {
                    if ("-1".equals(result)) {
                        errorLogger.error("【参数为空。信息、电话号码等有空指针，登陆失败】" + " errorcode:" + result);
                    } else if ("-2".equals(result)) {
                        errorLogger.error("【电话号码个数超过100】" + " errorcode:" + result);
                    } else if ("-10".equals(result)) {
                        errorLogger.error("【申请缓存空间失败】" + " errorcode:" + result);
                    } else if ("-11".equals(result)) {
                        errorLogger.error("【电话号码中有非数字字符】" + " errorcode:" + result);
                    } else if ("-12".equals(result)) {
                        errorLogger.error("【有异常电话号码】" + " errorcode:" + result);
                    } else if ("-13".equals(result)) {
                        errorLogger.error("【电话号码个数与实际个数不相等】" + " errorcode:" + result);
                    } else if ("-14".equals(result)) {
                        errorLogger.error("【实际号码个数超过100】" + " errorcode:" + result);
                    } else if ("-101".equals(result)) {
                        errorLogger.error("【发送消息等待超时】" + " errorcode:" + result);
                    } else if ("-102".equals(result)) {
                        errorLogger.error("【发送或接收消息失败】" + " errorcode:" + result);
                    } else if ("-103".equals(result)) {
                        errorLogger.error("【接收消息超时】" + " errorcode:" + result);
                    } else if ("-200".equals(result)) {
                        errorLogger.error("【其他错误】" + " errorcode:" + result);
                    } else if ("-999".equals(result)) {
                        errorLogger.error("【web服务器内部错误】" + " errorcode:" + result);
                    } else {
                        errorLogger.error("【其他值见附录,返回的字符串为： 】 errorcode:" + result);
                    }
                }
            } else if (400 == statusCode) {
                errorLogger.error("【400 Bad Request】");
            } else if (404 == statusCode) {
                errorLogger.error("【404 Not Found】");
            } else {
                errorLogger.error("【请求返回的状态码 statusCode： 】" + statusCode);
            }
        } catch (HttpException e) {
            errorLogger.error("【HttpException主要可能是在构造getMethod的时候传入的协议不对或者服务器端返回的内容不正常等】", e);
            throw new BusinessException("【HttpException主要可能是在构造getMethod的时候传入的协议不对或者服务器端返回的内容不正常等】");
        } catch (IOException e) {
            errorLogger.error("【IOException发生网络异常，HttpClient会根据你指定的恢复策略自动试着重新执行executeMethod方法】", e);
            throw new BusinessException("【IOException发生网络异常，HttpClient会根据你指定的恢复策略自动试着重新执行executeMethod方法】");
        } catch (Exception e) {
            errorLogger.error("【未知异常】", e);
            throw new BusinessException("【未知异常】");
        } finally {
            if (postMethod != null) {
                // 释放连接
                postMethod.releaseConnection();
            }
        }
        return isSuccess;
    }

    /**
     * 验证号码是不是黑名单
     *
     * @param tel
     * @param btype
     *
     * @return
     */
    public boolean isSmsBlack(String tel, Integer btype) {
        SmsBlackEntity entity = smsBlackDao.getSmsBlackByTel(tel, btype);
        if (entity != null) {
            return true;
        } else {
            return false;
        }
    }
}
