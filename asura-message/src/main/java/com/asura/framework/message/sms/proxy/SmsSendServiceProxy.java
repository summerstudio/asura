/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsSendServiceProxy 
 * @package com.asura.framework.message.sms.proxy 
 *
 * @date 2015/4/7 15:56 
 */
package com.asura.framework.message.sms.proxy;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.util.Check;
import com.asura.framework.base.util.JsonEntityTransform;
import com.asura.framework.conf.subscribe.ConfigSubscriber;
import com.asura.framework.message.constant.EnumSysConfig;
import com.asura.framework.message.entity.SmsInfoEntity;
import com.asura.framework.message.sms.api.SmsSendService;
import com.asura.framework.message.sms.constant.EnumSmsType;
import com.asura.framework.message.sms.entity.SmsInfoVO;
import com.asura.framework.message.sms.service.impl.SmsSendServiceImpl;
import com.asura.framework.message.sms.thread.SmsSendThread;
import com.asura.framework.message.sms.thread.SmsThreadPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> 短信发送代理 </P>
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
@Service("sms.smsSendServiceProxy")
public class SmsSendServiceProxy implements SmsSendService {

    /**
     * 订阅系统全局变量
     */
    private ConfigSubscriber sub = ConfigSubscriber.getInstance();

    /**
     * 手机号正则表但是
     */
    private static final String TEL_REGEX = "^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";

    @Resource(name = "sms.smsThreadpool")
    private SmsThreadPool smsThreadpool;

    @Resource(name = "sms.smsSendServiceImpl")
    private SmsSendServiceImpl smsSendServiceImpl;

    public static void main(String[] args) {
        System.out.println(Check.NuNStr(""));
    }

    @Override
    public String send(String jsonString) {
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNStr(jsonString)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("接口方法参数不能为空值 !");
            return dto.toJsonString();
        } else {
            SmsInfoVO vo = JsonEntityTransform.json2Entity(jsonString, SmsInfoVO.class);
            return sendSmsInfo(vo).toJsonString();
        }
    }

    @Override
    public DataTransferObject sendSmsInfoList(List<SmsInfoVO> vos) {
        DataTransferObject result = new DataTransferObject();
        StringBuffer msgs = new StringBuffer();
        DataTransferObject dto = null;
        for (SmsInfoVO vo : vos) {
            dto = sendSmsInfo(vo);
            if (dto.getCode() == 1) {
                result.setErrCode(DataTransferObject.ERROR);
                msgs.append("手机号：" + vo.getSmsTel() + " " + dto.getMsg() + "|");
            }
        }
        result.setMsg(msgs.toString());
        return result;
    }

    @Override
    public DataTransferObject sendSmsInfo(SmsInfoVO vo) {
        DataTransferObject dto = new DataTransferObject();
        if (vo != null) {
            if (!valiSmsType(vo.getMtype())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("短信类型错误 !" + vo.getMtype());
                return dto;
            }
            if (!valiSmsResouces(vo.getSource())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("短信来源错误! " + vo.getSource());
                return dto;
            }
            if (Check.NuNStr(vo.getSmsTel())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("手机号码为空值! ");
                return dto;
            }

            String[] tels = vo.getSmsTel().split("\\|");
            Set<String> rightSet = new HashSet<String>();
            Set<String> errorSet = new HashSet<String>();
            for (String tel : tels) {
                if (validateTel(tel)) {
                    rightSet.add(tel);
                } else {
                    errorSet.add(tel);
                }
            }

            SmsInfoEntity entity = null;
            for (String tel : rightSet) {
                entity = new SmsInfoEntity();
                entity.setMtype(vo.getMtype());
                entity.setSmsContent(vo.getSmsContent());
                entity.setSmsTel(tel);
                entity.setSource(vo.getSource());
                smsThreadpool.execute(new SmsSendThread(smsSendServiceImpl, entity, true));
            }

            if (errorSet.size() != 0) {
                dto.setMsg("短信发送成功! 但是, 部分手机号异常:" + errorSet.toString());
            }

        } else {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("接口参数为空值");
        }
        return dto;
    }

    /**
     * 验证类型
     *
     * @param type
     *
     * @return
     */
    private boolean valiSmsType(Integer type) {
        boolean flag = false;
        if (!Check.NuNObj(type)) {
            EnumSmsType[] types = EnumSmsType.values();
            for (EnumSmsType mt : types) {
                if (mt.getCode().intValue() == type) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;

    }

    /**
     * 验证来源
     *
     * @param source
     *
     * @return
     */
    private boolean valiSmsResouces(String source) {
        boolean flag = false;
        if (!Check.NuNStr(source)) {
            String resouces = sub.getConfigValue(EnumSysConfig.message_resources.getType(), EnumSysConfig.message_resources.getCode());
            if (Check.NuNStr(resouces)) {
                resouces = EnumSysConfig.message_resources.getDefaultValue(); // 系统默认值
            }
            String[] rss = resouces.split("\\|");
            for (String rs : rss) {
                if (rs.equalsIgnoreCase(source)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 验证手机号
     *
     * @param msgTel
     *
     * @return
     */
    private boolean validateTel(String msgTel) {
        Pattern p = Pattern.compile(TEL_REGEX.trim());
        Matcher m = p.matcher(msgTel);
        return m.matches();
    }
}
