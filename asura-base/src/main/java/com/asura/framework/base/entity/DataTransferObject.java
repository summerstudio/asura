/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file DataTransferObject 
 * @package com.asura.framework.base.entity 
 *
 * @date 2015/3/13 14:35 
 */
package com.asura.framework.base.entity;

import com.asura.framework.base.util.JsonEntityTransform;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 数据传输对象，包装数据传输内容 </P>
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
public class DataTransferObject implements Serializable {

    private static final long serialVersionUID = 3145149603266853073L;

    /**
     * 成功编码
     */
    public static final int SUCCESS = 0;

    /**
     * 失败编码
     */
    public static final int ERROR = 1;

    /**
     * 默认消息数据data的key值
     */
    public static final String DATA_KEY = "data";

    /**
     * 默认编码
     */
    private int code = 0;

    /**
     * 默认消息（错误消息）
     */
    private String msg = "";

    /**
     * 成功消息数据
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    public DataTransferObject() {

    }

    public DataTransferObject(final int code, final String msg, final Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setErrCode(final int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    /**
     * 向data中写入Entity
     *
     * @param key
     * @param value
     */
    public void putValue(final String key, final Object value) {
        data.put(key, value);
    }

    /**
     * 转换成json字符串
     *
     * @return
     */
    public String toJsonString() {
        return JsonEntityTransform.dataTransferObject2Json(this);
    }
}
