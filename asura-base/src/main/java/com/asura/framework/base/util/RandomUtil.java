/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file RandomUtil 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/18 12:13 
 */
package com.asura.framework.base.util;

import java.util.Random;

/**
 * <p> 随机数工具 </P>
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
public class RandomUtil {

    /**
     * 生成指定位数的数字串
     *
     * @param pwd_len
     *         随机数长度
     *
     * @return 指定位数的数字串
     */
    public static String genRandomNum(int pwd_len) {
        int maxNum = 1000;
        int i; // 生成的随机数
        int count = 0; // 生成的长度
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            i = i % 10;
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }
}
