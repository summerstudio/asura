/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationInnerSendService 
 * @package com.asura.framework.message.station.api.inner 
 *
 * @date 2015/4/7 16:28 
 */
package com.asura.framework.message.station.api.inner;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.paging.SearchModel;

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
public interface StationInnerSendService {

    /**
     * 分页查询站内信信息
     *
     * @param searchModel
     *
     * @return DataTransferObject code:0成功 -1失败  ;    dto.getData().get("data"):PagingResult<StationInfoEntity> 分页对象
     */
    public DataTransferObject findStationByPager(SearchModel searchModel);

    /**
     * 重发站内信
     *
     * @return DataTransferObject  code:0成功 -1失败 ;     dto.getData().get("data"):PagingResult<StationInfoEntity> 分页对象
     */
    public DataTransferObject repeatStation();
}
