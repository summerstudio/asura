/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsSendService 
 * @package com.asura.framework.message.sms.api 
 *
 * @date 2015/4/7 15:09 
 */
package com.asura.framework.message.sms.api;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.message.sms.entity.SmsInfoVO;

import java.util.List;

/**
 * <p> 发送短信接口 </P>
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
public interface SmsSendService {

    /**
     * 发送短信</br>
     * Integer mtype; 类型 1bug报警 2广告 3正常业务</br>
     * String source; 来自那个系统</br>
     * String smsTel; 手机号 多个|分开</br>
     * String smsContent; 短信内容</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * jsonString = {mtype:1,source:"order",smsTel:"",smsContent:"",timestamp:,sign:""}</br>
     *
     * @param jsonString
     *
     * @return
     */
    public String send(String jsonString);

    /**
     * 发送短信， 可以群发相同内容的短信， 手机号多个|分开</br>
     * Integer mtype; 类型 1bug报警 2广告 3正常业务</br>
     * String source; 来自那个系统</br>
     * String smsTel; 手机号 多个|分开</br>
     * String smsContent; 短信内容</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * @param vo
     *
     * @return DataTransferObject dto.getCode-0成功,1or-1失败;  dto.getMsg-描述
     */
    public DataTransferObject sendSmsInfo(SmsInfoVO vo);

    /**
     * 发送短信,群发不同内容的短信</br>
     * Integer mtype; 类型 1bug报警 2广告 3正常业务</br>
     * String source; 来自那个系统</br>
     * String smsTel; 手机号 多个|分开</br>
     * String smsContent; 短信内容</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * @param vos
     *
     * @return DataTransferObject dto.getCode-0成功,1or-1失败;  dto.getMsg-描述
     */
    public DataTransferObject sendSmsInfoList(List<SmsInfoVO> vos);
}
