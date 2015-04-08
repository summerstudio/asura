/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file EnumStationStatus 
 * @package com.asura.framework.message.station.constant 
 *
 * @date 2015/4/7 16:25 
 */
package com.asura.framework.message.station.constant;

/**
 * <p> 站内信状态枚举 </P>
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
public enum EnumStationStatus {

    /**
     * failure(1, "失败")
     */
    failure(1, "失败"),
    /**
     * success(2, "成功")
     */
    success(2, "成功");

    private int code;

    private String name;

    private EnumStationStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
