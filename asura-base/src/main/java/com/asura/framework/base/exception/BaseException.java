/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file BaseException 
 * @package com.asura.framework.base.exception 
 *
 * @date 2015/3/12 16:50 
 */
package com.asura.framework.base.exception;

/**
 * <p> 异常基类 </P>
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
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = -8983129509933867199L;

    /**
     * 构造器
     */
    public BaseException() {
        super();
    }

    /**
     * 构造器
     *
     * @param message
     *         异常详细信息
     * @param cause
     *         异常原因
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造器
     *
     * @param message
     *         异常详细信息
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * 构造器
     *
     * @param cause
     *         异常原因
     */
    public BaseException(Throwable cause) {
        super(cause);
    }
}
