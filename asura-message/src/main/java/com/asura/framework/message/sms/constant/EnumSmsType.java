/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file EnumSmsType 
 * @package com.asura.framework.message.sms.constant 
 *
 * @date 2015/4/7 15:07 
 */
package com.asura.framework.message.sms.constant;

/**
 * <p> 短信类型枚举 </P>
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
public enum EnumSmsType {

    /**
     * bug(1, "bug报警短信")
     */
    bug(1, "bug报警短信"),
    /**
     * ad(2, "广告短信")
     */
    ad(2, "广告短信"),
    /**
     * biz(3, "正常业务短信")
     */
    biz(3, "正常业务短信");

    private int code;

    private String name;

    private EnumSmsType(int code, String name) {
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
