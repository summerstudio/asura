/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationSendThread 
 * @package com.asura.framework.message.station.thread 
 *
 * @date 2015/4/7 16:37 
 */
package com.asura.framework.message.station.thread;

import com.asura.framework.message.entity.StationInfoEntity;
import com.asura.framework.message.station.entity.StationInfoVO;
import com.asura.framework.message.station.service.impl.StationSendServiceImpl;

/**
 * <p> 站内信操作线程 </P>
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
public class StationSendThread implements Runnable {

    private StationSendServiceImpl stationSendServiceImpl;

    private StationInfoVO vo;

    private StationInfoEntity entity;

    private boolean add; // true 增加   false 修改

    public StationSendThread(StationSendServiceImpl stationSendServiceImpl, StationInfoVO vo, StationInfoEntity entity, boolean add) {
        this.vo = vo;
        this.stationSendServiceImpl = stationSendServiceImpl;
        this.entity = entity;
        this.add = add;
    }

    @Override
    public void run() {
        if (add) {
            stationSendServiceImpl.sendFirstStation(vo);
        } else {
            stationSendServiceImpl.sendNoFirstStation(entity);
        }

    }
}
