/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file CacheAspect 
 * @package com.asura.framework.cache.redisannotate 
 *
 * @date 2015/3/24 16:25 
 */
package com.asura.framework.cache.redisannotate;

import com.asura.framework.base.util.JsonEntityTransform;
import com.asura.framework.conf.subscribe.ConfigSubscriber;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <p> 缓存切面 </P>
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
@Component
@Aspect
public class CacheAspect {

    @Resource(name = "redisServiceFactory")
    private RedisService redisService;

    /**
     * 是否可以使用缓存
     *
     * @return true 开启， false 关闭
     */
    private boolean cacheEnable() {
        String type = EnumSysConfig.asura_cacheEnable.getType();
        String code = EnumSysConfig.asura_cacheEnable.getCode();
        String value = ConfigSubscriber.getInstance().getConfigValue(type, code);
        if (value == null) {
            value = EnumSysConfig.asura_cacheEnable.getDefaultValue();
        }
        if ("0".equals(value)) { // 关闭缓存
            return false;
        } else {
            return true;
        }
    }

    /**
     * 定义缓存逻辑
     *
     * @param pjp
     *         用于环绕通知，使用proceed()方法来执行目标方法
     *
     * @return 缓存数据
     *
     * @throws Throwable
     */
    @Around("@annotation(com.asura.framework.cache.redisOne.Cache)")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        Method method = getMethod(pjp); // 获取被拦截方法对象
        Cache cache = method.getAnnotation(Cache.class); // 获取缓存注解对象
        Object result = null;

        if (!cache.selfControl()) {
            if (!cacheEnable()) { // 关闭缓存
                result = pjp.proceed();
                return result;
            }
        }

        switch (cache.dataStructure()) {
            case hash:
                result = hashCache(cache, method, pjp);
                break;
            case string:
                result = hashString(cache, method, pjp);
                break;

            default:
                break;
        }
        return result;

    }

    /**
     * 定义String结构的缓存逻辑
     *
     * @param cache
     *         缓存注解
     * @param method
     *         方法
     * @param pjp
     *         环绕通知点
     *
     * @return 结构化的数据
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings({"rawtypes"})
    private Object hashString(Cache cache, Method method, ProceedingJoinPoint pjp) throws InstantiationException, IllegalAccessException {
        int exTime = cache.expireTime();
        String key = parseKey(cache.key(), method, pjp.getArgs());
        Object result = redisService.get(key);
        //获取方法返回信息
        Class clazz = (Class) method.getGenericReturnType();// 返回类型
        Object retObj = clazz.newInstance();
        if (retObj instanceof String) { // 判断返回值是不是字符串类型
            if (result == null) {
                try {
                    result = pjp.proceed();
                    Assert.notNull(key, "key 不能为空值!!");
                    Assert.notNull(result, "key  result不能为空值!!");
                    redisService.set(key, result.toString(), exTime);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 定义hash结构的缓存逻辑
     *
     * @param cache
     *         缓存注解
     * @param method
     *         方法
     * @param pjp
     *         环绕通知点
     *
     * @return 结构化的数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Object hashCache(Cache cache, Method method, ProceedingJoinPoint pjp) {
        Object result = null;
        int exTime = cache.expireTime();
        String key = cache.key();
        String fieldKey = parseKey(cache.fieldKey(), method, pjp.getArgs());
        Class clazz = null;

        if (cache.isUpdate()) {
            try {
                result = pjp.proceed();
                Assert.notNull(fieldKey, "fieldKey 不能为空值!!");
                Assert.notNull(key, "key 不能为空值!!");
                redisService.hset(key, fieldKey, JsonEntityTransform.Object2Json(result), exTime);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            //获取方法返回信息
            Type returnType = method.getGenericReturnType();// 返回类型
            if (returnType instanceof ParameterizedType) { // 返回值中包含泛型
                Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();// 泛型类型列表
                for (Type type : types) {
                    clazz = (Class) type;
                }
                result = redisService.hgetToList(key, fieldKey, clazz);
            } else {
                clazz = (Class) returnType;
                result = redisService.hgetToEntity(key, fieldKey, clazz);
            }

            if (result == null) {
                try {
                    result = pjp.proceed();
                    Assert.notNull(fieldKey, "fieldKey 不能为空值!!");
                    Assert.notNull(key, "key 不能为空值!!");
                    redisService.hset(key, fieldKey, JsonEntityTransform.Object2Json(result), exTime);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    /**
     * 清除hash缓存
     *
     * @param pjp
     *         ProceedingJoinPoint
     *
     * @return
     */
    @Around(value = "@annotation(com.asura.framework.cache.redisOne.CacheDel)")
    public Object del(ProceedingJoinPoint pjp) {
        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            Method method = getMethod(pjp); // 获取被拦截方法对象
            CacheDel cacheDel = method.getAnnotation(CacheDel.class); // 获取缓存注解对象
            if (cacheDel.selfControl()) { // 自控缓存，不受外部控制
                String key = cacheDel.key();
                String fieldKey = parseKey(cacheDel.fieldKey(), method, pjp.getArgs());
                redisService.hdel(key, fieldKey);
            }
            if (cacheEnable()) { // 关闭缓存
                String key = cacheDel.key();
                String fieldKey = parseKey(cacheDel.fieldKey(), method, pjp.getArgs());
                redisService.hdel(key, fieldKey);
            }

        }
        return result;
    }

    /**
     * 保存缓存， 修改缓存
     *
     * @param pjp
     *         ProceedingJoinPoint
     *
     * @return
     *
     * @throws Throwable
     */
    @Around(value = "@annotation(com.asura.framework.cache.redisOne.CacheSave)")
    public Object save(ProceedingJoinPoint pjp) throws Throwable {
        Method method = getMethod(pjp); // 获取被拦截方法对象
        CacheSave cacheSave = method.getAnnotation(CacheSave.class); // 获取缓存注解对象
        Object result = null;

        if (!cacheSave.selfControl()) {
            if (!cacheEnable()) { // 关闭缓存
                result = pjp.proceed();
                return result;
            }
        }

        switch (cacheSave.dataStructure()) {
            case hash:
                result = saveHashCache(cacheSave, method, pjp);
                break;
            case string:
                result = saveHashString(cacheSave, method, pjp);
                break;

            default:
                break;
        }
        return result;

    }

    /**
     * 保存缓存信息
     *
     * @param cacheSave
     *         CacheSave
     * @param method
     *         Method
     * @param pjp
     *         ProceedingJoinPoint
     *
     * @return
     */
    private Object saveHashCache(CacheSave cacheSave, Method method, ProceedingJoinPoint pjp) {
        Object result = null;
        int exTime = cacheSave.expireTime();
        String key = cacheSave.key();
        String fieldKey = parseKey(cacheSave.fieldKey(), method, pjp.getArgs());
        try {
            result = pjp.proceed();
            Assert.notNull(fieldKey, "fieldKey 不能为空值!!");
            Assert.notNull(key, "key 不能为空值!!");
            Assert.notNull(result, "key result不能为空值!!");
            redisService.hset(key, fieldKey, JsonEntityTransform.Object2Json(result), exTime);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存缓存
     *
     * @param cacheSave
     *         CacheSave
     * @param method
     *         Method
     * @param pjp
     *         ProceedingJoinPoint
     *
     * @return
     */
    private Object saveHashString(CacheSave cacheSave, Method method, ProceedingJoinPoint pjp) {
        int exTime = cacheSave.expireTime();
        String key = parseKey(cacheSave.key(), method, pjp.getArgs());
        Object result = null;
        try {
            result = pjp.proceed();
            Assert.notNull(key, "key 不能为空值!!");
            Assert.notNull(result, "key  result不能为空值!!");
            redisService.set(key, result.toString(), exTime);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取被拦截方法对象, MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     * 而缓存的注解在实现类的方法上, 所以应该使用反射获取当前对象的方法对象
     *
     * @param pjp
     *         ProceedingJoinPoint
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return method;

    }

    /**
     * 获取缓存的key
     * key 定义在注解上，支持SPEL表达式
     *
     * @param key
     *         redis key
     * @param method
     *         Method
     * @param args
     *         参数
     *
     * @return 结构化的key
     */
    private String parseKey(String key, Method method, Object[] args) {
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
