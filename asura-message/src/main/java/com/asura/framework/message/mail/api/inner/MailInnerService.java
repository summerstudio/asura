/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MailInnerService 
 * @package com.asura.framework.message.mail.api.inner 
 *
 * @date 2015/4/7 11:13 
 */
package com.asura.framework.message.mail.api.inner;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.message.entity.MailBlackEntity;

/**
 * <p> 监控平台使用接口 </P>
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
public interface MailInnerService {

    /**
     * 分页查询邮件信息
     *
     * @param searchModel
     *         查询对象
     *
     * @return code:0成功 -1失败      dto.getData().get("data"):PagingResult<MailInfoEntity> 分页对象
     */
    DataTransferObject findMailByPager(SearchModel searchModel);

    /**
     * 根据id查询邮件信息
     *
     * @param id
     *         邮件id
     *
     * @return code:0成功 -1失败;dto.getData().get("data"):MailInfoEntity 邮件信息对象
     */
    DataTransferObject getMailInfoById(Integer id);

    /**
     * 分页查询黑名单信息
     *
     * @param searchModel
     *         查询对象
     *
     * @return code:0成功 -1失败      dto.getData().get("data"):PagingResult<MailBlackEntity>分页对象
     */
    DataTransferObject findMailBlackByPager(SearchModel searchModel);

    /**
     * 添加黑名单</br>
     *
     * @param entity
     *
     * @return code:0成功 -1失败      dto.getData().get("data"):添加记录id
     */
    DataTransferObject saveMailBlack(MailBlackEntity entity);

    /**
     * 删除黑名单</br>
     *
     * @param ids
     *
     * @return code:0成功 -1失败      dto.getData().get("data"):删除数量
     */
    DataTransferObject deleteMailBlack(String ids);

    /**
     * 重新发送</br>
     *
     * @return dto.getData().get("data")：重发数量
     */
    DataTransferObject repeatSend();
}
