/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailSendService 
 * @package com.asura.framework.message.mail.api 
 *
 * @date 2015/4/7 10:54 
 */
package com.asura.framework.message.mail.api;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.message.mail.entity.MailInfoVO;

import java.util.List;

/**
 * <p> 邮件发送接口，支持entity发送也支持json格式发送 </P>
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
public interface MailSendService {

    /**
     * 发送邮件</br>
     * Integer mtype; 类型 1bug报警 2广告 3正常业务邮件</br>
     * String source; 来自那个系统</br>
     * String mailAddrs; 邮件地址多个|分开</br>
     * String subject;  邮件主题</br>
     * String content; 邮件内容</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * jsonString = {mtype:1,source:"order",mailAddr:"xxx@lvye.com",subject:"",context:"",timestamp:,sign:""}
     *
     * @param jsonString
     *
     * @return
     */
    public String send(String jsonString);

    /**
     * 发送邮件, 可以群发相同内容的邮件, 邮件地址多个|分开</br>
     * Integer mtype; 类型 1bug报警 2广告 3正常业务邮件</br>
     * String source; 来自那个系统</br>
     * String mailAddrs; 邮件地址多个|分开</br>
     * String subject;  邮件主题</br>
     * String content; 邮件内容</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * @param vo
     *
     * @return DataTransferObject dto.getCode-0成功,1or-1失败;  dto.getMsg-描述
     */
    public DataTransferObject sendMailInfo(MailInfoVO vo);

    /**
     * 发送邮件, 群发不同内容的邮件</br>
     * Integer mtype; 类型 1bug报警 2广告 3正常业务邮件</br>
     * String source; 来自那个系统</br>
     * String mailAddrs; 邮件地址多个|分开</br>
     * String subject;  邮件主题</br>
     * String content; 邮件内容</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * @param vos
     *
     * @return DataTransferObject dto.getCode-0成功,1or-1失败;  dto.getMsg-描述
     */
    public DataTransferObject sendMailInfoList(List<MailInfoVO> vos);
}
