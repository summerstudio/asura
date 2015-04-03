/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file RedisService 
 * @package com.asura.framework.cache.redisannotate 
 *
 * @date 2015/3/23 10:50 
 */
package com.asura.framework.cache.redisannotate;

import com.asura.framework.base.entity.BaseEntity;
import com.asura.framework.cache.redisannotate.callback.Callback;

import java.util.List;
import java.util.Map;

/**
 * <p> redis服务接口 </P>
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
public interface RedisService {

    /**
     * 成功标志
     */
    String SUCCESS = "OK";

    /**
     * 默认缓存失效时间.默认失效时间为60分钟
     */
    int EXPIRE = 1 * 60 * 60;

    /**
     * 读取字符串类型缓存. 提供默认回调, 默认回调将直接返回缓存数据.
     *
     * @return
     */
    String get(String key);

    /**
     * 读取字符串类型缓存. 提供用户自定义回调
     *
     * @param key
     *         待查找的缓存Key
     * @param callback
     *         回调
     *
     * @return
     */
    String get(String key, Callback<String> callback);

    /**
     * 保存缓存内容
     *
     * @param key
     *         缓存Key
     * @param value
     *         缓存内容
     */
    boolean set(String key, String value);

    /**
     * 保存缓存内容. 提供用户自定义缓存失效时间.
     *
     * @param key
     *         缓存Key
     * @param value
     *         缓存内容
     * @param expire
     *         缓存失效时间
     *
     * @return
     */
    boolean set(String key, String value, int expire);

    /**
     * 增加缓存值数据
     *
     * @param key
     *         缓存Key
     *
     * @return 返回新值
     */
    long increment(String key);

    /**
     * 增加缓存值数据
     *
     * @param key
     *         缓存Key
     * @param expire
     *         缓存失效时间
     *
     * @return 返回新值
     */
    long increment(String key, int expire);

    /**
     * 增加缓存值数据
     *
     * @param key
     *         缓存Key
     * @param expire
     *         缓存失效时间
     * @param callback
     *         回调
     *
     * @return 返回新值
     */
    long increment(String key, int expire, Callback<Long> callback);

    /**
     * 保存Map数据结构缓存信息. 默认的缓存失效时间.
     *
     * @param key
     *         缓存Key
     * @param value
     *         缓存Value
     *
     * @return
     */
    boolean saveMap(String key, Map<String, String> value);

    /**
     * 保存Map数据结构缓存信息. 提供用户自定义缓存失效时间
     *
     * @param key
     *         缓存Key
     * @param value
     *         缓存Value
     * @param expire
     *         缓存失效时间
     *
     * @return
     */
    boolean saveMap(String key, Map<String, String> value, int expire);

    /**
     * 读取Map数据结构中指定的字段值. 提供默认的回调
     *
     * @param key
     *         缓存key
     * @param field
     *         缓存field
     *
     * @return 缓存值
     */
    String hget(String key, String field);

    /**
     * 读取Map数据结构中指定的字段值. 提供用户自定义回调接口.
     *
     * @param key
     *         缓存key
     * @param field
     *         缓存field
     * @param callback
     *         回调函数
     *
     * @return 缓存值
     */
    String hget(String key, String field, Callback<String> callback);

    /**
     * 读取hash缓存中的指定的字段值
     *
     * @param key
     *         缓存key
     * @param field
     *         缓存field
     * @param clazz
     *         转换目标对象类型
     * @param <T>
     *         返回结构化的值
     *
     * @return 返回结构化的值
     */
    <T extends BaseEntity> T hgetToEntity(final String key, final String field, final Class<T> clazz);

    /**
     * 读取hash缓存中的指定的字段值
     *
     * @param key
     *         缓存key
     * @param field
     *         缓存field
     * @param clazz
     *         转换目标对象类型
     * @param <T>
     *         返回结构化的值
     *
     * @return 返回结构化的值
     */
    <T> List<T> hgetToList(final String key, final String field, final Class<T> clazz);

    /**
     * 设置Map数据结构中指定Hash字段的值.
     *
     * @param key
     *         缓存key
     * @param field
     *         缓存field
     * @param value
     *         缓存值
     */
    boolean hset(String key, String field, String value);

    /**
     * 设置Map数据结构中指定Hash字段的值.并重新设置Map的失效时间.
     *
     * @param key
     *         缓存key
     * @param field
     *         缓存field
     * @param value
     *         缓存值
     * @param expire
     *         过期时间
     *
     * @return 是否成功
     */
    boolean hset(String key, String field, String value, int expire);

    /**
     * 删除hash缓存
     *
     * @param key
     *         缓存key
     * @param fields
     *         hash key
     *
     * @return 删除记录数
     */
    Long hdel(String key, String... fields);

    /**
     * 设置Map数据结构中多个Hash字段的值.并重新设置Map的失效时间
     *
     * @param key
     *         缓存key
     * @param hash
     *         缓存值
     *
     * @return 是否成功
     */
    boolean hmset(final String key, final Map<String, String> hash);

    /**
     * 设置Map数据结构中多个Hash字段的值.并重新设置Map的失效时间
     *
     * @param key
     *         缓存key
     * @param hash
     *         缓存值
     * @param expire
     *         过期时间
     *
     * @return 是否成功
     */
    boolean hmset(final String key, final Map<String, String> hash, int expire);

    /**
     * 读取Map数据结构中所有的字段值, 并封装成用户指定的对象
     *
     * @param key
     *         缓存key
     * @param clazz
     *         转换目标类型
     *
     * @return 缓存值
     */
    <T> T hgetAll(String key, Class<T> clazz);

    /**
     * 读取Map数据结构中所有的字段值, 并提供用户子定义回调接口.
     *
     * @param <T>
     *         返回目标类型
     * @param key
     *         缓存key
     * @param callback
     *         回调方法
     */
    <T> T hgetAll(String key, Callback<T> callback);

    /**
     * 将多个值存入List数据结构. List数据结构可存在重复数据.
     *
     * @param key
     *         缓存key
     * @param value
     *         缓存值
     *
     * @return 是否成功
     */
    boolean lpush(String key, String... value);
}
