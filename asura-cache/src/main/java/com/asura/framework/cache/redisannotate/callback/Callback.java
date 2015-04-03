/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file Callback 
 * @package com.asura.framework.cache.redisannotate.callback 
 *
 * @date 2015/3/23 10:53 
 */
package com.asura.framework.cache.redisannotate.callback;

import com.asura.framework.cache.redisannotate.exception.RedisException;

import java.util.Map;

/**
 * <p> 回调接口 </P>
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
public interface Callback<T> {

    /**
     * string类型处理结果的默认回调
     */
    public final static Callback<String> STRINGCALLBACK = new NOOPCallback();

    /**
     * long类型处理结果的默认回调
     */
    public final static Callback<Long> LONGCALLBACK = new LONGCallback();

    /**
     * 默认回调接口, 对返回值不做任何处理.
     */
    public static class NOOPCallback implements Callback<String> {

        @Override
        public String callback(final String value) {
            return value;
        }

        @Override
        public String callback(final Map<String, String> map) {
            throw new RedisException("no support callback(Map<String, String> map).");
        }

    }

    /**
     * 默认回调接口, 对返回值不做任何处理.
     */
    public static class LONGCallback implements Callback<Long> {

        @Override
        public Long callback(final Long t) {
            return t;
        }

        @Override
        public Long callback(final Map<String, String> map) {
            throw new RedisException("no support callback(Map<String, String> map).");
        }
    }

    /**
     * 回调. 根据返回值构建指定的数据模型
     *
     * @param t
     *
     * @return
     *
     * @throws Exception
     */
    T callback(T t) throws Exception;

    /**
     * 回调. 根据返回值Map类型构建指定的数据模型.
     *
     * @param map
     *
     * @return
     *
     * @throws Exception
     */
    T callback(Map<String, String> map) throws Exception;
}
