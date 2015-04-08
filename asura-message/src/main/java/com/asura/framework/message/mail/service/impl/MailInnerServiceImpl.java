/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailInnerServiceImpl 
 * @package com.asura.framework.message.mail.service.impl 
 *
 * @date 2015/4/7 14:13 
 */
package com.asura.framework.message.mail.service.impl;

import com.asura.framework.base.exception.BusinessException;
import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.message.entity.MailBlackEntity;
import com.asura.framework.message.entity.MailInfoEntity;
import com.asura.framework.message.mail.dao.MailBlackDao;
import com.asura.framework.message.mail.dao.MailDao;
import com.asura.framework.message.mail.thread.MailThreadPool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> 具体实现类 </P>
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
@Service("mail.mailInnerServiceImpl")
public class MailInnerServiceImpl {

    @Resource(name = "mail.mailDao")
    private MailDao mailDao;

    @Resource(name = "mail.mailBlackDao")
    private MailBlackDao mailBlackDao;

    @Resource(name = "mail.threadPool")
    private MailThreadPool threadPool;

    public PagingResult<MailInfoEntity> findMailByPager(SearchModel searchModel) {
        return mailDao.findMailByPager(searchModel);
    }

    public MailInfoEntity getMailInfoById(Integer id) {
        return mailDao.getMailInfoById(id);
    }

    public PagingResult<MailBlackEntity> findMailBlackByPager(SearchModel searchModel) {
        return mailBlackDao.findMailBlackByPager(searchModel);
    }

    @Transactional(value = "transactionManagerMessage")
    public int saveMailBlack(MailBlackEntity entity) {
        MailBlackEntity mbe = mailBlackDao.getMailBlackByMailAddr(entity.getMailAddr(), entity.getBtype());
        if (mbe != null) {
            throw new BusinessException("不能重复添加黑名单!");
        }
        int id = mailBlackDao.saveMailBlackEntity(entity);
        if (id == 0) {
            throw new BusinessException("没有添加进去,返回id为0!");
        }
        return id;
    }

    @Transactional(value = "transactionManagerMessage")
    public int batchDeleteMailBlackEntity(String ids) {
        int count = mailBlackDao.batchDeleteMailBlackEntity(ids);
        if (count == 0) {
            throw new BusinessException("没有对应的id!");
        }
        return count;
    }

    /**
     * 查询失败的记录
     *
     * @return
     */
    public List<MailInfoEntity> repeatSend() {
        List<MailInfoEntity> mes = this.mailDao.repeatEmail();
        return mes;
    }
}
