/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsInnerServiceProxy 
 * @package com.asura.framework.message.sms.proxy 
 *
 * @date 2015/4/7 16:11 
 */
package com.asura.framework.message.sms.proxy;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.message.entity.SmsBlackEntity;
import com.asura.framework.message.entity.SmsInfoEntity;
import com.asura.framework.message.sms.api.inner.SmsInnerService;
import com.asura.framework.message.sms.service.impl.SmsInnerSendServiceImpl;
import com.asura.framework.message.sms.service.impl.SmsSendServiceImpl;
import com.asura.framework.message.sms.thread.SmsSendThread;
import com.asura.framework.message.sms.thread.SmsThreadPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> 监控平台接口代理 </P>
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
@Service("sms.smsInnerServiceProxy")
public class SmsInnerServiceProxy implements SmsInnerService {

    @Resource(name = "sms.smsThreadpool")
    private SmsThreadPool smsThreadpool;

    @Resource(name = "sms.smsInnerSendServiceImpl")
    private SmsInnerSendServiceImpl smsInnerSendServiceImpl;

    @Resource(name = "sms.smsSendServiceImpl")
    private SmsSendServiceImpl smsSendServiceImpl;

    @Override
    public DataTransferObject findSmsByPager(SearchModel searchModel) {
        DataTransferObject dto = new DataTransferObject();
        try {
            PagingResult<SmsInfoEntity> pr = smsInnerSendServiceImpl.findMailByPager(searchModel);
            dto.putValue(DataTransferObject.DATA_KEY, pr);
        } catch (Exception e) {
            e.printStackTrace();
            dto.setErrCode(-1);
            dto.setMsg("短信分页查询异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject findSmsBlackByPager(SearchModel searchModel) {
        DataTransferObject dto = new DataTransferObject();
        try {
            PagingResult<SmsBlackEntity> pr = smsInnerSendServiceImpl.findSmsBlackByPager(searchModel);
            dto.putValue(DataTransferObject.DATA_KEY, pr);
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("短信黑名单分页查询异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject saveSmsBlack(SmsBlackEntity entity) {
        DataTransferObject dto = new DataTransferObject();
        try {
            int id = smsInnerSendServiceImpl.saveSmsBlackEntity(entity);
            dto.putValue(DataTransferObject.DATA_KEY, id);
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("短信黑名单保存异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject deleteSmsBlack(String ids) {
        DataTransferObject dto = new DataTransferObject();
        try {
            int count = smsInnerSendServiceImpl.batchDeleteSmsBlackEntity(ids);
            dto.putValue(DataTransferObject.DATA_KEY, count);
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("短信黑名单删除异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject repeatSend() {
        DataTransferObject dto = new DataTransferObject();
        try {
            List<SmsInfoEntity> entitys = smsInnerSendServiceImpl.repeatSms(1);
            for (SmsInfoEntity entity : entitys) {
                smsThreadpool.execute(new SmsSendThread(smsSendServiceImpl, entity, false));
            }
            dto.putValue(DataTransferObject.DATA_KEY, entitys.size());
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("短信黑名单删除异常!");
        }
        return dto;
    }
}
