/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file EnumSysConfig 
 * @package com.asura.framework.file.openresty 
 *
 * @date 2015/4/14 17:01 
 */
package com.asura.framework.file.openresty;

import com.asura.framework.conf.subscribe.AsuraSub;

/**
 * <p> openresty提供的上传图片操作地址 </P>
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
@AsuraSub
public enum EnumSysConfig {

    asura_openresty_image_url("asura", "openrestyImageUrl", "http://192.168.0.17:8888/image", "上传图片地址");

    private String type;
    private String code;
    private String defaultValue;
    private String notes;

    private EnumSysConfig(String type, String code, String defaultValue, String notes) {
        this.code = code;
        this.type = type;
        this.notes = notes;
        this.defaultValue = defaultValue;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *         the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *         the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes
     *         the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue
     *         the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
