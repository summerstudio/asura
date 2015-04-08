/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailDao 
 * @package com.asura.framework.message.mail.dao 
 *
 * @date 2015/4/7 11:19 
 */
package com.asura.framework.message.mail.dao;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.dao.ibatis.BaseIbatisDaoContext;
import com.asura.framework.message.entity.MailInfoEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 邮件操作DAO </P>
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
@Repository("mail.mailDao")
public class MailDao {

    public static final String SQLMAP_NAMESPACE = "com.asura.framework.message.mail.mailDao";

    @Resource(name = "messageBaseIbatisDao")
    private BaseIbatisDaoContext messageBaseIbatisDao;

    /**
     * 保存邮件
     *
     * @param entity
     *         邮件实体
     *
     * @return
     */
    public int saveMailInfoEntity(MailInfoEntity entity) {
        return Integer.class.cast(this.messageBaseIbatisDao.save(SQLMAP_NAMESPACE + ".saveMailInfoEntity", entity));
    }

    /**
     * 分页查询邮件列表
     *
     * @param searchModel
     *         查询模型
     *
     * @return
     */
    public PagingResult<MailInfoEntity> findMailByPager(SearchModel searchModel) {
        Map<String, Object> params = this.messageBaseIbatisDao.getConditionMap(searchModel);
        params.put("firstRowIndex", searchModel.getFirstRowIndex());
        params.put("pageSize", searchModel.getPageSize());
        List<MailInfoEntity> rows = this.messageBaseIbatisDao.findAll(SQLMAP_NAMESPACE + ".findMailInfoEntityByCondition", MailInfoEntity.class, params);
        long total = this.messageBaseIbatisDao.count(SQLMAP_NAMESPACE + ".countMailInfoEntityByCondition", params);
        return new PagingResult<MailInfoEntity>(total, rows);
    }

    /**
     * 根据ID查询邮件详细信息
     *
     * @param id
     *         邮件ID
     *
     * @return
     */
    public MailInfoEntity getMailInfoById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return this.messageBaseIbatisDao.findOne(SQLMAP_NAMESPACE + ".getMailInfoById", MailInfoEntity.class, params);
    }

    /**
     * 查询发送失败的邮件列表
     *
     * @return
     */
    public List<MailInfoEntity> repeatEmail() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mailStatus", 1);
        return this.messageBaseIbatisDao.findAll(SQLMAP_NAMESPACE + ".repeatEmail", MailInfoEntity.class, params);
    }

    /**
     * 更新邮件信息
     *
     * @param entity
     *         邮件信息实体
     *
     * @return
     */
    public int updateMailInfoEntity(MailInfoEntity entity) {
        return this.messageBaseIbatisDao.update(SQLMAP_NAMESPACE + ".updateMailInfoEntity", entity);
    }
}
