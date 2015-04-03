/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file Primitive 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/19 17:58 
 */
package com.asura.framework.base.util;

/**
 * <p> 原始数据类型的格式化操作 </P>
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
public class Primitive {

    /**
     * 将字符串转化为int类型数值, 若转化失败, 则返回为默认的转化值
     *
     * @param s
     *         目标字符串
     * @param defaultValue
     *         默认值
     *
     * @return 转化后的值
     */
    public static final int parseInt(final String s, final int defaultValue) {
        return parseInt(s, defaultValue, 10);
    }

    /**
     * 将字符串转化为int类型数值, 若转化失败, 则返回为默认的转化值
     *
     * @param s
     *         目标字符串
     * @param defaultValue
     *         默认值
     * @param radix
     *         X进制
     *
     * @return 转换后的值
     */
    public static final int parseInt(final String s, final int defaultValue, final int radix) {
        int result = defaultValue;

        if (s == null || s.trim().isEmpty()) {
            return result;
        }

        try {
            result = Integer.parseInt(s, radix);
        } catch (final NumberFormatException ignore) {
        }
        return result;
    }

    /**
     * 私有构造器
     */
    private Primitive() {
        throw new AssertionError("Uninstantiable class");
    }
}
