/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ParameterRedisException 
 * @package com.asura.framework.cache.redisannotate.exception 
 *
 * @date 2015/3/23 10:58 
 */
package com.asura.framework.cache.redisannotate.exception;

/**
 * <p> Redis请求参数有误. 参数为空. </P>
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
public class ParameterRedisException extends RedisException {

    private static final long serialVersionUID = 5328751522882804710L;

    public ParameterRedisException(String message) {
        super(message);
    }
}
