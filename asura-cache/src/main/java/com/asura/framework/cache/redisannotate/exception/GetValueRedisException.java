/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file GetValueRedisException 
 * @package com.asura.framework.cache.redisannotate.exception 
 *
 * @date 2015/3/23 11:03 
 */
package com.asura.framework.cache.redisannotate.exception;

/**
 * <p> 获取redis缓存数据异常 </P>
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
public class GetValueRedisException extends RedisException {

    private static final long serialVersionUID = 9199115422886016448L;

    public GetValueRedisException(String message) {
        super(message);
    }

    public GetValueRedisException(String message, Throwable cause) {
        super(message, cause);
    }
}
