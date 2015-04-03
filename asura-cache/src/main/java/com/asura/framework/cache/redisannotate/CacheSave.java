/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file CacheSave 
 * @package com.asura.framework.cache.redisannotate 
 *
 * @date 2015/3/24 16:17 
 */
package com.asura.framework.cache.redisannotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 保存修改缓存 </P>
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
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheSave {

    DataStructure dataStructure() default DataStructure.hash;

    String key();

    String fieldKey() default "";

    int expireTime() default 3600;

    boolean selfControl() default false;
}
