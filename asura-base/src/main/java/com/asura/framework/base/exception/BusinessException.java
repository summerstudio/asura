/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file BusinessException 
 * @package com.asura.framework.base.exception 
 *
 * @date 2015/3/12 16:55 
 */
package com.asura.framework.base.exception;

/**
 * <p> 业务异常基类 </P>
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
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 3080959240023221144L;

    /**
     * 构造器
     */
    public BusinessException() {
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
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造器
     *
     * @param message
     *         异常详细信息
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * 构造器
     *
     * @param cause
     *         异常原因
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }
}
