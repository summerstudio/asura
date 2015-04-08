/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationSendService 
 * @package com.asura.framework.message.station.api 
 *
 * @date 2015/4/7 16:26 
 */
package com.asura.framework.message.station.api;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.message.station.entity.StationInfoVO;

import java.util.List;

/**
 * <p> 站内信接口 </P>
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
public interface StationSendService {

    /**
     * 发送站内信</br>
     * String uid; 发送者</br>
     * String touid; 接受者 多个|分开</br>
     * String msg; 内容</br>
     * String source; 来源</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * jsonString = {uid:"",touid:"order",msg:"",source:"",timestamp:,sign:""}</br>
     *
     * @param jsonString
     *
     * @return
     */
    public String send(String jsonString);

    /**
     * 发送站内信, 可以群发内容相同的站内信， touid使用多个|分开</br>
     * String uid; 发送者</br>
     * Integer touid; 接受者 多个|分开</br>
     * String msg; 内容</br>
     * String source; 来源</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * @param vo
     *
     * @return DataTransferObject dto.getCode-0成功,1or-1失败;  dto.getMsg-描述
     */
    public DataTransferObject sendStationInfo(StationInfoVO vo);

    /**
     * 发送站内信， 群发不同内容的站内信</br>
     * String uid; 发送者</br>
     * Integer touid; 接受者 多个|分开</br>
     * String msg; 内容</br>
     * String source; 来源</br>
     * Integer timestamp; 时间戳</br>
     * String sign; 接口签名</br>
     *
     * @param vos
     *
     * @return DataTransferObject dto.getCode-0成功,1or-1失败;  dto.getMsg-描述
     */
    public DataTransferObject sendStationInfoList(List<StationInfoVO> vos);
}
