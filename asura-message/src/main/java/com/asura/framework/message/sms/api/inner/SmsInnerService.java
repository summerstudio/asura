/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SmsInnerService 
 * @package com.asura.framework.message.sms.api.inner 
 *
 * @date 2015/4/7 15:11 
 */
package com.asura.framework.message.sms.api.inner;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.message.entity.SmsBlackEntity;

/**
 * <p> 监控平台短信接口 </P>
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
public interface SmsInnerService {

    /**
     * 分页查询邮件信息</br>
     *
     * @param searchModel
     *
     * @return code:0成功 -1失败      dto.getData().get("data"):PagingResult<SmsInfoEntity>
     */
    DataTransferObject findSmsByPager(SearchModel searchModel);

    /**
     * 分页查询黑名单信息</br>
     *
     * @param searchModel
     *
     * @return code:0成功 -1失败      dto.getData().get("data"):PagingResult<SmsBlackEntity>
     */
    DataTransferObject findSmsBlackByPager(SearchModel searchModel);

    /**
     * 添加黑名单</br>
     *
     * @param entity
     *
     * @return code:0成功 -1失败      dto.getData().get("data"):id
     */
    DataTransferObject saveSmsBlack(SmsBlackEntity entity);

    /**
     * 删除黑名单</br>
     *
     * @param ids
     *
     * @return code:0成功 -1失败      dto.getData().get("data"):count
     */
    DataTransferObject deleteSmsBlack(String ids);

    /**
     * 重新发送</br>
     *
     * @return dto.getData().get("data")：重发数量
     */
    DataTransferObject repeatSend();
}
