/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MapRedisException 
 * @package com.asura.framework.cache.redisannotate.exception 
 *
 * @date 2015/3/23 11:00 
 */
package com.asura.framework.cache.redisannotate.exception;

/**
 * <p> Map数据结构操作异常 </P>
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
public class MapRedisException extends RedisException {

    private static final long serialVersionUID = 7089979006721838112L;

    public MapRedisException(String message) {
        super(message);
    }

    public MapRedisException(String message, Throwable cause) {
        super(message, cause);
    }
}
