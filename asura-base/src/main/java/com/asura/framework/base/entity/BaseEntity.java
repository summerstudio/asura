/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved.
 *
 * @project asura
 * @file BaseEntity
 * @package com.asura.framework.base.entity
 *
 * @date 2015/3/10 16:09
 */
package com.asura.framework.base.entity;

import com.asura.framework.base.util.JsonEntityTransform;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 实体基类 </P>
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
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8339499669797253682L;

    /**
     * toString方法，返回属性名称及值
     *
     * @return 属性名称及值，格式：[name]张三 [sex]男
     */
    @Override
    public String toString() {
        final StringBuffer result = new StringBuffer();
        try {
            Class<? extends Object> clazz = this.getClass();
            Field[] fields = clazz.getDeclaredFields();
            String fieldName;
            String methodName;
            Method method;
            Object resultObj;
            for (Field field : fields) {
                fieldName = field.getName();
                if ("serialVersionUID".equals(fieldName)) {
                    continue;
                }
                methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                method = clazz.getMethod(methodName);
                resultObj = method.invoke(this);
                if (resultObj != null && !"".equals(resultObj)) {
                    result.append("[").append(fieldName).append("]").append(resultObj).append(" ");
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 对象转换成map
     *
     * @return 转换后的Map
     */
    public Map<String, Object> toMap() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Class<? extends Object> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String fieldName;
        String methodName;
        for (Field field : fields)
            try {
                fieldName = field.getName();
                if ("serialVersionUID".equals(fieldName)) {
                    continue;
                }
                methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                resultMap.put(fieldName, clazz.getMethod(methodName).invoke(this));
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        return resultMap;
    }

    /**
     * 根据字段转换json串
     *
     * @param paramNames
     *         实体类字段名称
     *
     * @return json串
     */
    public String toJsonStr(String... paramNames) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> map = this.toMap();
        for (String pn : paramNames) {
            if (map.get(pn) != null) {
                jsonMap.put(pn, map.get(pn));
            }
        }
        return JsonEntityTransform.Object2Json(jsonMap);
    }

    /**
     * 将实体对象转换成json串
     *
     * @return json串
     */
    public String toJsonStr() {
        return JsonEntityTransform.Object2Json(this);
    }
}
