/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file IncrementRedisException 
 * @package com.asura.framework.cache.redisannotate.exception 
 *
 * @date 2015/3/23 11:02 
 */
package com.asura.framework.cache.redisannotate.exception;

/**
 * <p> redis递增操作异常 </P>
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
public class IncrementRedisException extends RedisException {

    private static final long serialVersionUID = 1639224677601281876L;

    public IncrementRedisException(String message) {
        super(message);
    }

    public IncrementRedisException(String message, Throwable cause) {
        super(message, cause);
    }
}
