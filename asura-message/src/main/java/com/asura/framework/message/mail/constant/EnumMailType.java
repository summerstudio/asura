/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file EnumMailType 
 * @package com.asura.framework.message.mail.constant 
 *
 * @date 2015/4/7 11:17 
 */
package com.asura.framework.message.mail.constant;

/**
 * <p> 邮件类型 </P>
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
public enum EnumMailType {

    /**
     * bug(1, "bug报警邮件"),
     */
    bug(1, "bug报警邮件"),
    /**
     * ad(2, "广告邮件")
     */
    ad(2, "广告邮件"),
    /**
     * biz(3, "正常业务邮件")
     */
    biz(3, "正常业务邮件");

    private int code;

    private String name;

    private EnumMailType(int code, String name) {
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
