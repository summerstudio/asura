/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file Check 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/18 10:45 
 */
package com.asura.framework.base.util;

import java.util.Collection;
import java.util.Map;

/**
 * <p> 检测工具 </P>
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
public class Check {

    /**
     * 检测Java对象是否为空.
     *
     * @param obj
     *         待检测的对象
     *
     * @return true: 空; false:非空.
     */
    public static boolean NuNObj(final Object obj) {
        if (obj instanceof String) {
            return NuNStr((String) obj);
        }
        return obj == null;
    }

    /**
     * 检测Java对象是否为空. 同时检测多个指定的对象, 如果存在一个为空, 则全部为空.
     *
     * @param objs
     *         待检测的对象
     *
     * @return true: 空; false:非空.
     */
    public static boolean NuNObjs(final Object... objs) {
        for (final Object obj : objs) {
            final boolean nun = NuNObj(obj);
            if (nun) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测Java对象是否为空. 同时检测多个指定的对象, 如果存在一个为空, 则全部为空.
     *
     * @param objs
     *         待检测的对象
     *
     * @return true: 空; false:非空.
     */
    public static boolean NuNObject(final Object[] objs) {
        if ((objs == null) || (objs.length == 0)) {
            return true;
        }
        for (final Object obj : objs) {
            final boolean nun = NuNObj(obj);
            if (nun) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测字符串是否为空.
     * <p>
     * 1. 未分配内存
     * </p>
     * <p>
     * 2. 字符串剔除掉前后空格后的长度为0
     * </p>
     *
     * @param str
     *         待检测的字符串
     *
     * @return true: 空; false:非空.
     */
    public static boolean NuNStr(final String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    /**
     * 严格的检测字符串是否为空.
     * <p>
     * 1. 未分配内存
     * </p>
     * <p>
     * 2. 字符串剔除掉前后空格后的长度为0
     * </p>
     * <p>
     * 3. 字符串为'null'字串.
     * </p>
     *
     * @param str
     *         待检测的字符串
     *
     * @return true: 空; false:非空.
     */
    public static boolean NuNStrStrict(final String str) {
        return NuNStr(str) || "null".equalsIgnoreCase(str);
    }

    /**
     * 判断集合是否为空
     *
     * @param colls
     *         待检测的集合
     *
     * @return true: 空; false:非空.
     */
    public static boolean NuNCollection(final Collection<?> colls) {
        return (colls == null) || colls.isEmpty();
    }

    /**
     * 判断Map集合是否为空
     *
     * @param map
     *         待检测的集合
     *
     * @return true: 空; false:非空.
     */
    public static boolean NuNMap(final Map<?, ?> map) {
        return (map == null) || map.isEmpty();
    }

    private Check() {
        throw new AssertionError("Uninstantiable class");
    }
}
