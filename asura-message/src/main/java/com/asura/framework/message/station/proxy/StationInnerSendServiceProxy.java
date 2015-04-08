/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationInnerSendServiceProxy 
 * @package com.asura.framework.message.station.proxy 
 *
 * @date 2015/4/7 16:48 
 */
package com.asura.framework.message.station.proxy;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.message.entity.StationInfoEntity;
import com.asura.framework.message.station.api.inner.StationInnerSendService;
import com.asura.framework.message.station.service.impl.StationInnerSendServiceImpl;
import com.asura.framework.message.station.service.impl.StationSendServiceImpl;
import com.asura.framework.message.station.thread.StationSendThread;
import com.asura.framework.message.station.thread.StationThreadPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> 监控平台站内信业务接口代理实现 </P>
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
@Service("station.stationInnerSendServiceProxy")
public class StationInnerSendServiceProxy implements StationInnerSendService {

    @Resource(name = "station.stationThreadPool")
    private StationThreadPool stationThreadPool;

    @Resource(name = "station.stationSendServiceImpl")
    private StationSendServiceImpl stationSendServiceImpl;

    @Resource(name = "station.stationInnerSendServiceImpl")
    private StationInnerSendServiceImpl stationInnerSendServiceImpl;

    @Override
    public DataTransferObject findStationByPager(SearchModel searchModel) {
        DataTransferObject dto = new DataTransferObject();
        try {
            PagingResult<StationInfoEntity> pr = stationInnerSendServiceImpl.findStationByPager(searchModel);
            dto.putValue(DataTransferObject.DATA_KEY, pr);
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("站内信分页查询异常!");
        }
        return dto;
    }

    @Override
    public DataTransferObject repeatStation() {
        DataTransferObject dto = new DataTransferObject();
        try {
            List<StationInfoEntity> list = stationInnerSendServiceImpl.repeatStation(1);
            for (StationInfoEntity entity : list) {
                stationThreadPool.execute(new StationSendThread(stationSendServiceImpl, null, entity, false));
            }
            dto.putValue(DataTransferObject.DATA_KEY, list.size());
        } catch (Exception e) {
            dto.setErrCode(-1);
            dto.setMsg("站内信重发异常!");
        }
        return dto;
    }
}
