/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailBlackDao 
 * @package com.asura.framework.message.mail.dao 
 *
 * @date 2015/4/7 11:27 
 */
package com.asura.framework.message.mail.dao;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.dao.ibatis.BaseIbatisDaoContext;
import com.asura.framework.message.entity.MailBlackEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 邮件黑名单操作DAO </P>
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
@Repository("mail.mailBlackDao")
public class MailBlackDao {

    public static final String SQLMAP_NAMESPACE = "com.asura.framework.message.mail.mailBlackDao";

    @Resource(name = "messageBaseIbatisDao")
    private BaseIbatisDaoContext messageBaseIbatisDao;

    /**
     * 保存邮件黑名单
     *
     * @param entity
     *         黑名单实体
     *
     * @return
     */
    public int saveMailBlackEntity(MailBlackEntity entity) {
        return Integer.class.cast(this.messageBaseIbatisDao.save(SQLMAP_NAMESPACE + ".saveMailBlackEntity", entity));
    }

    /**
     * 依据条件查询黑名单
     *
     * @param mailAddr
     *         邮件地址
     * @param btype
     *         黑名单类型
     *
     * @return
     */
    public MailBlackEntity getMailBlackByMailAddr(String mailAddr, Integer btype) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mailAddr", mailAddr);
        params.put("btype", btype);
        return this.messageBaseIbatisDao.findOne(SQLMAP_NAMESPACE + ".getMailBlackByMailAddr", MailBlackEntity.class, params);
    }

    /**
     * 分页查询黑名单列表
     *
     * @param searchModel
     *         查询模型
     *
     * @return
     */
    public PagingResult<MailBlackEntity> findMailBlackByPager(SearchModel searchModel) {
        Map<String, Object> params = this.messageBaseIbatisDao.getConditionMap(searchModel);
        params.put("firstRowIndex", searchModel.getFirstRowIndex());
        params.put("pageSize", searchModel.getPageSize());
        List<MailBlackEntity> rows = this.messageBaseIbatisDao.findAll(SQLMAP_NAMESPACE + ".findMailBlackEntityByCondition", MailBlackEntity.class, params);
        long total = this.messageBaseIbatisDao.count(SQLMAP_NAMESPACE + ".countMailBlackEntityByCondition", params);
        return new PagingResult<MailBlackEntity>(total, rows);
    }

    /**
     * 依据给定ID查询黑名单详细信息
     *
     * @param ids
     *         id列表
     *
     * @return
     */
    public int batchDeleteMailBlackEntity(String ids) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        return this.messageBaseIbatisDao.update(SQLMAP_NAMESPACE + ".batchDeleteMailBlackEntity", params);
    }
}
