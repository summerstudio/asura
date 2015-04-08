/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailInnerServiceProxy 
 * @package com.asura.framework.message.mail.proxy 
 *
 * @date 2015/4/7 14:34 
 */
package com.asura.framework.message.mail.proxy;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.exception.BusinessException;
import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.message.entity.MailBlackEntity;
import com.asura.framework.message.entity.MailInfoEntity;
import com.asura.framework.message.mail.api.inner.MailInnerService;
import com.asura.framework.message.mail.service.impl.MailInnerServiceImpl;
import com.asura.framework.message.mail.service.impl.MailSendServiceImpl;
import com.asura.framework.message.mail.thread.MailSendThread;
import com.asura.framework.message.mail.thread.MailThreadPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> 监控平台使用接口实现 </P>
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
@Service("mail.mailInnerServiceProxy")
public class MailInnerServiceProxy implements MailInnerService {

    @Resource(name = "mail.mailInnerServiceImpl")
    private MailInnerServiceImpl mailInnerServiceImpl;

    @Resource(name = "mail.mailSendServiceImpl")
    private MailSendServiceImpl mailSendServiceImpl;

    @Resource(name = "mail.threadPool")
    private MailThreadPool threadPool;

    @Override
    public DataTransferObject findMailByPager(SearchModel searchModel) {
        DataTransferObject dto = new DataTransferObject();
        try {
            PagingResult<MailInfoEntity> pr = mailInnerServiceImpl.findMailByPager(searchModel);
            dto.putValue(DataTransferObject.DATA_KEY, pr);
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("邮件分页查询异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject findMailBlackByPager(SearchModel searchModel) {
        DataTransferObject dto = new DataTransferObject();
        try {
            PagingResult<MailBlackEntity> pr = mailInnerServiceImpl.findMailBlackByPager(searchModel);
            dto.putValue(DataTransferObject.DATA_KEY, pr);
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("分页查询黑名单信息异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject saveMailBlack(MailBlackEntity entity) {
        DataTransferObject dto = new DataTransferObject();
        try {
            int id = mailInnerServiceImpl.saveMailBlack(entity);
            dto.putValue(DataTransferObject.DATA_KEY, id);
        } catch (BusinessException e) {
            dto.setErrCode(-1);
            dto.setMsg(e.getMessage());
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("添加邮件黑名单异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject deleteMailBlack(String ids) {
        DataTransferObject dto = new DataTransferObject();
        try {
            int count = mailInnerServiceImpl.batchDeleteMailBlackEntity(ids);
            dto.putValue(DataTransferObject.DATA_KEY, count);
        } catch (BusinessException e) {
            dto.setErrCode(-1);
            dto.setMsg(e.getMessage());
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("删除黑名单异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject getMailInfoById(Integer id) {
        DataTransferObject dto = new DataTransferObject();
        try {
            MailInfoEntity entity = mailInnerServiceImpl.getMailInfoById(id);
            dto.putValue(DataTransferObject.DATA_KEY, entity);
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("根据id查询邮件信息!");
        }
        return dto;
    }

    @Override
    public DataTransferObject repeatSend() {
        DataTransferObject dto = new DataTransferObject();
        try {
            List<MailInfoEntity> mes = mailInnerServiceImpl.repeatSend();
            for (MailInfoEntity me : mes) {
                threadPool.execute(new MailSendThread(mailSendServiceImpl, me, false));
            }
            dto.putValue(DataTransferObject.DATA_KEY, mes.size());
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("重发邮件异常!");
        }
        return dto;
    }
}
