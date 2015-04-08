/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailSendServiceProxy 
 * @package com.asura.framework.message.mail.proxy 
 *
 * @date 2015/4/7 14:38 
 */
package com.asura.framework.message.mail.proxy;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.util.Check;
import com.asura.framework.base.util.JsonEntityTransform;
import com.asura.framework.conf.subscribe.ConfigSubscriber;
import com.asura.framework.message.constant.EnumSysConfig;
import com.asura.framework.message.entity.MailInfoEntity;
import com.asura.framework.message.mail.api.MailSendService;
import com.asura.framework.message.mail.constant.EnumMailType;
import com.asura.framework.message.mail.entity.MailInfoVO;
import com.asura.framework.message.mail.service.impl.MailSendServiceImpl;
import com.asura.framework.message.mail.thread.MailSendThread;
import com.asura.framework.message.mail.thread.MailThreadPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>  </P>
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
@Service("mail.mailSendServiceProxy")
public class MailSendServiceProxy implements MailSendService {

    @Resource(name = "mail.threadPool")
    private MailThreadPool threadPool;

    @Resource(name = "mail.mailSendServiceImpl")
    private MailSendServiceImpl mailSendServiceImpl;

    /**
     * 订阅系统全局变量
     */
    private ConfigSubscriber sub = ConfigSubscriber.getInstance();

    /**
     * email地址正则表达式
     */
    private static final String EMAIL_REGEX = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    @Override
    public String send(String jsonString) {
        MailInfoVO vo = JsonEntityTransform.json2Entity(jsonString, MailInfoVO.class);
        DataTransferObject dto = sendMailInfo(vo);
        return dto.toJsonString();
    }

    @Override
    public DataTransferObject sendMailInfoList(List<MailInfoVO> vos) {
        DataTransferObject result = new DataTransferObject();
        StringBuffer msgs = new StringBuffer();
        DataTransferObject dto = null;
        for (MailInfoVO vo : vos) {
            dto = sendMailInfo(vo);
            if (dto.getCode() == 1) {
                result.setErrCode(DataTransferObject.ERROR);
                msgs.append("邮箱：" + vo.getMailAddrs() + " " + dto.getMsg() + "|");
            }
        }
        result.setMsg(msgs.toString());
        return result;
    }

    @Override
    public DataTransferObject sendMailInfo(MailInfoVO vo) {
        DataTransferObject dto = new DataTransferObject();
        dto = validata(vo);
        if (dto.getCode() == DataTransferObject.ERROR) {
            return dto;
        }

        String mailAddrs = vo.getMailAddrs();
        if (Check.NuNStr(mailAddrs)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("邮箱地址不能为空值。");
            return dto;
        }

        String[] addrs = mailAddrs.split("\\|");
        for (String addr : addrs) {
            if (!addr.matches(EMAIL_REGEX)) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("邮箱地址格式错误。" + addr);
                break;
            }
        }
        if (dto.getCode() == DataTransferObject.ERROR) {
            return dto;
        }
        MailInfoEntity entity = null;
        for (String addr : addrs) {
            entity = new MailInfoEntity();
            entity.setContent(vo.getContent());
            entity.setMailAddr(addr);
            entity.setMtype(vo.getMtype());
            entity.setSource(vo.getSource());
            entity.setSubject(vo.getSubject());
            threadPool.execute(new MailSendThread(mailSendServiceImpl, entity, true));
        }
        return dto;
    }

    /**
     * 验证邮件信息
     *
     * @param vo
     *
     * @return
     */
    private DataTransferObject validata(MailInfoVO vo) {
        DataTransferObject dto = new DataTransferObject();
        if (vo == null) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("数据格式有误，请核实。");
        } else {
            if (Check.NuNStr(vo.getMailAddrs())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("邮件不能为空。");
            } else if (Check.NuNStr(vo.getSubject())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("邮件主题不能为空。");
            } else if (Check.NuNStr(vo.getContent())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("邮件内容不能为空。");
            } else if (!valiMailResouces(vo.getSource())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("邮件来源不正确。");
            } else if (!valiMailType(vo.getMtype())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("邮件类型不正确。");
            } else {
                dto.setErrCode(DataTransferObject.SUCCESS);
                dto.setMsg("成功");
            }
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
    private boolean valiMailType(Integer type) {
        boolean flag = false;
        if (!Check.NuNObj(type)) {
            EnumMailType[] types = EnumMailType.values();
            for (EnumMailType mt : types) {
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
    private boolean valiMailResouces(String source) {
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
}
