/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationInnerSendServiceImpl 
 * @package com.asura.framework.message.station.service.impl 
 *
 * @date 2015/4/7 16:44 
 */
package com.asura.framework.message.station.service.impl;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.message.entity.StationInfoEntity;
import com.asura.framework.message.station.dao.StationDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> 监控平台接口业务实现 </P>
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
@Service("station.stationInnerSendServiceImpl")
public class StationInnerSendServiceImpl {

    @Resource(name = "station.stationDao")
    private StationDao stationDao;

    public PagingResult<StationInfoEntity> findStationByPager(SearchModel searchModel) {
        return stationDao.findStationByPager(searchModel);
    }

    public List<StationInfoEntity> repeatStation(Integer status) {
        return stationDao.repeatStation(status);
    }
}
