/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsBlackDao 
 * @package com.asura.framework.message.sms.dao 
 *
 * @date 2015/4/7 15:16 
 */
package com.asura.framework.message.sms.dao;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.dao.ibatis.BaseIbatisDaoContext;
import com.asura.framework.message.entity.SmsBlackEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 短信黑名单数据层操作 </P>
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
@Repository("sms.smsBlackDao")
public class SmsBlackDao {

    public static final String SQLMAP_NAMESPACE = "com.asura.framework.message.sms.smsBlackDao";

    @Resource(name = "messageBaseIbatisDao")
    private BaseIbatisDaoContext messageBaseIbatisDao;

    public int saveSmsBlackEntity(SmsBlackEntity entity) {
        return Integer.class.cast(this.messageBaseIbatisDao.save(SQLMAP_NAMESPACE + ".saveSmsBlackEntity", entity));
    }

    public SmsBlackEntity getSmsBlackByTel(String tel, Integer btype) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tel", tel);
        params.put("btype", btype);
        return this.messageBaseIbatisDao.findOne(SQLMAP_NAMESPACE + ".getSmsBlackByTel", SmsBlackEntity.class, params);
    }

    public PagingResult<SmsBlackEntity> findSmsBlackByPager(SearchModel searchModel) {
        Map<String, Object> params = this.messageBaseIbatisDao.getConditionMap(searchModel);
        params.put("firstRowIndex", searchModel.getFirstRowIndex());
        params.put("pageSize", searchModel.getPageSize());
        List<SmsBlackEntity> rows = this.messageBaseIbatisDao.findAll(SQLMAP_NAMESPACE + ".findSmsBlackEntityByCondition", SmsBlackEntity.class, params);
        long total = this.messageBaseIbatisDao.count(SQLMAP_NAMESPACE + ".countSmsBlackEntityByCondition", params);
        return new PagingResult<SmsBlackEntity>(total, rows);
    }

    public int batchDeleteSmsBlackEntity(String ids) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        return this.messageBaseIbatisDao.update(SQLMAP_NAMESPACE + ".batchDeleteSmsBlackEntity", params);
    }
}
