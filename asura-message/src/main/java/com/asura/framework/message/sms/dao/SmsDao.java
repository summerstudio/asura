/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsDao 
 * @package com.asura.framework.message.sms.dao 
 *
 * @date 2015/4/7 15:31 
 */
package com.asura.framework.message.sms.dao;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.dao.ibatis.BaseIbatisDaoContext;
import com.asura.framework.message.entity.SmsInfoEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 短信持久化操作 </P>
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
@Repository("sms.SmsDao")
public class SmsDao {

    public static final String SQLMAP_NAMESPACE = "com.asura.framework.message.sms.smsDao";

    @Resource(name = "messageBaseIbatisDao")
    private BaseIbatisDaoContext messageBaseIbatisDao;

    public int saveSmsInfoEntity(SmsInfoEntity entity) {
        return Integer.class.cast(this.messageBaseIbatisDao.save(SQLMAP_NAMESPACE + ".saveSmsInfoEntity", entity));
    }

    public PagingResult<SmsInfoEntity> findMailByPager(SearchModel searchModel) {
        Map<String, Object> params = this.messageBaseIbatisDao.getConditionMap(searchModel);
        params.put("firstRowIndex", searchModel.getFirstRowIndex());
        params.put("pageSize", searchModel.getPageSize());
        List<SmsInfoEntity> rows = this.messageBaseIbatisDao.findAll(SQLMAP_NAMESPACE + ".findSmsInfoEntityByCondition", SmsInfoEntity.class, params);
        long total = this.messageBaseIbatisDao.count(SQLMAP_NAMESPACE + ".countSmsInfoEntityByCondition", params);
        return new PagingResult<SmsInfoEntity>(total, rows);
    }

    public List<SmsInfoEntity> repeatSms(Integer status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("smsStatus", status);
        return this.messageBaseIbatisDao.findAll(SQLMAP_NAMESPACE + ".repeatSms", SmsInfoEntity.class, params);
    }

    public int updateSmsInfoEntity(SmsInfoEntity entity) {
        return this.messageBaseIbatisDao.update(SQLMAP_NAMESPACE + ".updateSmsInfoEntity", entity);
    }
}
