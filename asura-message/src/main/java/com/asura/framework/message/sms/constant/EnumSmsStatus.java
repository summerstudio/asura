/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file EnumSmsStatus 
 * @package com.asura.framework.message.sms.constant 
 *
 * @date 2015/4/7 15:08 
 */
package com.asura.framework.message.sms.constant;

/**
 * <p> 短信状态枚举 </P>
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
public enum EnumSmsStatus {

    /**
     * failure(1, "失败")
     */
    failure(1, "失败"),
    /**
     * success(2, "成功")
     */
    success(2, "成功"),
    /**
     * black(3, "黑名单")
     */
    black(3, "黑名单"),
    /**
     * delay(4, "延迟发送")
     */
    delay(4, "延迟发送"),
    /**
     * inqueue(5, "正在排队发送")
     */
    inqueue(5, "正在排队发送");

    private int code;

    private String name;

    private EnumSmsStatus(int code, String name) {
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
