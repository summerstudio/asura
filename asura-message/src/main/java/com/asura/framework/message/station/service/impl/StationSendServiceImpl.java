/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationSendServiceImpl 
 * @package com.asura.framework.message.station.service.impl 
 *
 * @date 2015/4/7 16:38 
 */
package com.asura.framework.message.station.service.impl;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.util.AsuraHttpClient;
import com.asura.framework.base.util.Check;
import com.asura.framework.base.util.JsonEntityTransform;
import com.asura.framework.logback.ErrorLog;
import com.asura.framework.message.entity.StationInfoEntity;
import com.asura.framework.message.station.dao.StationDao;
import com.asura.framework.message.station.entity.StationInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 站内信业务层实现 </P>
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
@Service("station.stationSendServiceImpl")
public class StationSendServiceImpl {

    private static final Logger errorLogger = LoggerFactory.getLogger(ErrorLog.class);

    @Resource(name = "station.stationDao")
    private StationDao stationDao;

    @Value("#{'${station.url}'}")
    private String station_url;


    /**
     * 首次发送
     *
     * 参数：
     * uid 发送者uid
     * touid 接受者uid, 多个逗号分开
     * msg 发送的消息
     * 返回值：
     * {"code":200,"msg":"请求成功，返回200","data":{"data":"{\"error_code\":1}"}}
     * error_code 0：成功
     * error_code 1：缺少参数
     * error_code 非0 非1：错误的touid
     *
     * @param vo
     */
    public synchronized void sendFirstStation(StationInfoVO vo) {
        int stationStatus = 2;
        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("uid", vo.getUid());
            param.put("touid", vo.getTouid());
            if (!Check.NuNObj(vo.getMsg())) {
                String msg = vo.getMsg().replaceAll("\"", "").replaceAll("'", "");
                param.put("msg", msg);
            }
            DataTransferObject dto = post(param);
            int code = dto.getCode();
            if (code != 200) {
                stationStatus = 1;
                errorLogger.error("站内信发送失败，参数：" + vo.toJsonStr() + " 返回值：" + dto.toJsonString());
            } else {
                stationStatus = 2;
            }
        } catch (Exception e) {
            stationStatus = 1;
            errorLogger.error("站内信发送异常：" + vo.toJsonStr(), e);
        } finally {
            String[] touids = vo.getTouid().split("\\|");
            StationInfoEntity entity = null;
            for (String touid : touids) {
                entity = new StationInfoEntity();
                entity.setMsg(vo.getMsg());
                entity.setSendDate(new Timestamp(System.currentTimeMillis()));
                entity.setSource(vo.getSource());
                entity.setStationStatus(stationStatus);
                entity.setTouid(Integer.parseInt(touid));
                entity.setUid(Integer.parseInt(vo.getUid()));
                stationDao.saveStationInfoEntity(entity);
            }
        }
    }

    /**
     * 重新发送
     *
     * 参数：
     * uid 发送者uid
     * touid 接受者uid, 多个逗号分开
     * msg 发送的消息
     * 返回值：
     * {"code":200,"msg":"请求成功，返回200","data":{"data":"{\"error_code\":1}"}}
     * error_code 0：成功
     * error_code 1：缺少参数
     * error_code 非0 非1：错误的touid
     *
     * @param entity
     */
    @SuppressWarnings("unchecked")
    public void sendNoFirstStation(StationInfoEntity entity) {
        int stationStatus = 2;
        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("uid", entity.getUid() + "");
            param.put("touid", entity.getTouid() + ""); // 可以多个， 用逗号分开
            if (!Check.NuNObj(entity.getMsg())) {
                String msg = entity.getMsg().replaceAll("\"", "").replaceAll("'", "");
                param.put("msg", msg);
            }

            DataTransferObject dto = post(param);
            int code = dto.getCode();
            if (code != 200) {
                errorLogger.error("站内信发送失败，参数：" + entity.toJsonStr() + " 返回值：" + dto.toJsonString());
                stationStatus = 1;
            } else {
                String postResult = (String) dto.getData().get(DataTransferObject.DATA_KEY);
                Map<String, Object> postResultMap = (Map<String, Object>) JsonEntityTransform.json2Map(postResult);
                String errorCode = postResultMap.get("error_code").toString();
                if ("0".equals(errorCode)) {
                    stationStatus = 2;
                } else if ("1".equals(errorCode)) {
                    stationStatus = 1;
                } else {
                    stationStatus = 1;
                }
            }
        } catch (Exception e) {
            stationStatus = 1;
            errorLogger.error("重新发送 站内信发送异常：" + entity.toJsonStr(), e);
        } finally {
            entity.setStationStatus(stationStatus);
            entity.setSendDate(new Timestamp(System.currentTimeMillis()));
            entity.setRetryNum(entity.getRetryNum().intValue() + 1);
            stationDao.updateStationEntity(entity);
        }
    }

    public DataTransferObject post(Map<String, String> param) {
        String url = station_url;
        DataTransferObject dto = AsuraHttpClient.getInstance().post(url, param);
        return dto;
    }
}
