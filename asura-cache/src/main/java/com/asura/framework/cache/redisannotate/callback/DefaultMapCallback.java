/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file DefaultMapCallback 
 * @package com.asura.framework.cache.redisannotate.callback 
 *
 * @date 2015/3/23 16:06 
 */
package com.asura.framework.cache.redisannotate.callback;

import com.asura.framework.base.util.Check;
import com.asura.framework.base.util.Primitive;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * <p> Map数据结构转换为实体对象 </P>
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
public class DefaultMapCallback<T> extends CallbackAdapter<T> {

    private final Class<T> clazz;

    public DefaultMapCallback(final Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T callback(final Map<String, String> map) throws Exception {
        if (Check.NuNObj(clazz)) {
            return null;
        }

        final T object = clazz.newInstance();
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            final Field[] fields = superClass.getDeclaredFields();
            for (final Field field : fields) {
                final String fieldName = field.getName();
                final String value = map.get(fieldName);
                if (Check.NuNStrStrict(value)) {
                    continue;
                }

                field.setAccessible(true);
                if (field.getType() == int.class) {
                    field.set(object, Primitive.parseInt(value, -1));
                } else if (field.getType() == boolean.class) {
                    field.set(object, Boolean.parseBoolean(value));
                } else {
                    field.set(object, value);
                }
            }

        }
        return object;
    }
}
