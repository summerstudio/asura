/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file RedisException 
 * @package com.asura.framework.cache.redisannotate.exception 
 *
 * @date 2015/3/23 10:56 
 */
package com.asura.framework.cache.redisannotate.exception;

/**
 * <p> redis操作异常基类 </P>
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
public class RedisException extends RuntimeException {

    private static final long serialVersionUID = -828888521345126937L;

    public RedisException(final String message) {
        super(message);
    }

    public RedisException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
