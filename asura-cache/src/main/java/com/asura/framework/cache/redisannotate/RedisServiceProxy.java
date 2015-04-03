/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file RedisServiceProxy 
 * @package com.asura.framework.cache.redisannotate 
 *
 * @date 2015/3/23 16:39 
 */
package com.asura.framework.cache.redisannotate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p> RedisService服务代理 </P>
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
public class RedisServiceProxy implements InvocationHandler {

    private RedisService target;

    public RedisService bind(final RedisService target) {
        this.target = target;
        return (RedisService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            result = method.invoke(target, args);
        } finally {
        }
        return result;
    }
}
