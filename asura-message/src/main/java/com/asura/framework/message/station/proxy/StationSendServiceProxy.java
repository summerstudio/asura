/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationSendServiceProxy 
 * @package com.asura.framework.message.station.proxy 
 *
 * @date 2015/4/7 16:46 
 */
package com.asura.framework.message.station.proxy;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.util.Check;
import com.asura.framework.base.util.JsonEntityTransform;
import com.asura.framework.conf.subscribe.ConfigSubscriber;
import com.asura.framework.message.constant.EnumSysConfig;
import com.asura.framework.message.station.api.StationSendService;
import com.asura.framework.message.station.entity.StationInfoVO;
import com.asura.framework.message.station.service.impl.StationSendServiceImpl;
import com.asura.framework.message.station.thread.StationSendThread;
import com.asura.framework.message.station.thread.StationThreadPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> 站内信业务接口代理实现 </P>
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
@Service("station.stationSendServiceProxy")
public class StationSendServiceProxy implements StationSendService {

    /**
     * 订阅系统全局变量
     */
    private ConfigSubscriber sub = ConfigSubscriber.getInstance();

    @Resource(name = "station.stationThreadPool")
    private StationThreadPool stationThreadPool;

    @Resource(name = "station.stationSendServiceImpl")
    private StationSendServiceImpl stationSendServiceImpl;

    @Override
    public String send(String jsonString) {
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNStr(jsonString)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数不能为空值！");
            return dto.toJsonString();
        } else {
            return sendStationInfo(JsonEntityTransform.json2Entity(jsonString, StationInfoVO.class)).toJsonString();
        }
    }

    @Override
    public DataTransferObject sendStationInfoList(List<StationInfoVO> vos) {
        DataTransferObject result = new DataTransferObject();
        StringBuffer msgs = new StringBuffer();
        DataTransferObject dto = null;
        for (StationInfoVO vo : vos) {
            dto = sendStationInfo(vo);
            if (dto.getCode() == 1) {
                result.setErrCode(DataTransferObject.ERROR);
                msgs.append("touid：" + vo.getTouid() + " " + dto.getMsg() + "|");
            }
        }
        result.setMsg(msgs.toString());
        return result;
    }

    @Override
    public DataTransferObject sendStationInfo(StationInfoVO vo) {
        DataTransferObject dto = new DataTransferObject();
        if (vo != null) {
            if (!valiResouces(vo.getSource())) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("来源不正确！");
                return dto;
            }
            stationThreadPool.execute(new StationSendThread(stationSendServiceImpl, vo, null, true));
        } else {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("接口参数为空值！");
        }
        return dto;
    }

    /**
     * 验证来源
     *
     * @param source
     *
     * @return
     */
    private boolean valiResouces(String source) {
        boolean flag = false;
        if (!Check.NuNStr(source)) {
            String resouces = sub.getConfigValue(EnumSysConfig.message_resources.getType(), EnumSysConfig.message_resources.getCode());
            if (Check.NuNStr(resouces)) {
                resouces = EnumSysConfig.message_resources.getDefaultValue(); // 系统默认值
            }
            String[] rss = resouces.split("\\|");
            for (String rs : rss) {
                if (rs.equalsIgnoreCase(source)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}
