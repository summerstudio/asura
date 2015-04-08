/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsInnerSendServiceImpl 
 * @package com.asura.framework.message.sms.service.impl 
 *
 * @date 2015/4/7 15:53 
 */
package com.asura.framework.message.sms.service.impl;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.message.entity.SmsBlackEntity;
import com.asura.framework.message.entity.SmsInfoEntity;
import com.asura.framework.message.sms.dao.SmsBlackDao;
import com.asura.framework.message.sms.dao.SmsDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> 监控平台短信管理实现类 </P>
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
@Service("sms.smsInnerSendServiceImpl")
public class SmsInnerSendServiceImpl {

    @Resource(name = "sms.SmsDao")
    private SmsDao smsDao;

    @Resource(name = "sms.smsBlackDao")
    private SmsBlackDao smsBlackDao;

    @Transactional(value = "transactionManagerMessage")
    public int saveSmsBlackEntity(SmsBlackEntity entity) {
        return smsBlackDao.saveSmsBlackEntity(entity);
    }

    public PagingResult<SmsBlackEntity> findSmsBlackByPager(SearchModel searchModel) {
        return smsBlackDao.findSmsBlackByPager(searchModel);
    }

    @Transactional(value = "transactionManagerMessage")
    public int batchDeleteSmsBlackEntity(String ids) {
        return smsBlackDao.batchDeleteSmsBlackEntity(ids);
    }

    public PagingResult<SmsInfoEntity> findMailByPager(SearchModel searchModel) {
        return smsDao.findMailByPager(searchModel);
    }

    public List<SmsInfoEntity> repeatSms(Integer status) {
        return smsDao.repeatSms(status);
    }
}
