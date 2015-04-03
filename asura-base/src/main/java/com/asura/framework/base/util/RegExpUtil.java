/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file RegExpUtil 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/18 11:43 
 */
package com.asura.framework.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;

/**
 * <p> 正则表达式工具 </P>
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
public class RegExpUtil {

    public static final String REG_MOBILE = "^1[3|5|7|8|][0-9]{9}$";
    //邮箱正则表达式
    public static final String REG_EMAIL = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";

    public static final String IDENTIFY_CARD_NUM = "^(\\d{18}|\\d{15}|\\d{17}[xX])$";

    public static final String CHINESE = "^[\\u4e00-\\u9fa5]*$";

    public static final String PASSPORT = "^[A-Za-z0-9]{1,20}$";

    public static final String JG = "^[\\u4e00-\\u9fa5a-zA-Z0-9]{1,20}$";

    private static String[] valCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};

    private static String[] wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
    // 地区编码
    private static final Hashtable<String, String> AREACODE_HASHTABLE = new Hashtable<String, String>();

    /**
     * 验证手机号
     *
     * @param mobilePhoneNum
     *         手机号
     *
     * @return
     */
    public static boolean isMobilePhoneNum(final String mobilePhoneNum) {
        if (mobilePhoneNum == null || "".equals(mobilePhoneNum))
            return false;
        return mobilePhoneNum.matches(REG_MOBILE);
    }

    /**
     * 验证邮箱地址
     *
     * @param emailAddress
     *         邮箱地址
     *
     * @return
     */
    public static boolean isEmailAddress(final String emailAddress) {
        if (emailAddress == null || "".equals(emailAddress))
            return false;
        return emailAddress.matches(REG_EMAIL);
    }

    /**
     * 验证身份证号码
     *
     * @param idNum
     *         身份证号码
     *
     * @return
     */
    public static boolean idIdentifyCardNum(final String idNum) {
        if (idNum == null || "".equals(idNum))
            return false;
        if (!idNum.matches(IDENTIFY_CARD_NUM)) {
            return false;
        }
        String ai = "";
        if (idNum.length() == 18) {
            ai = idNum.substring(0, 17);
        } else if (idNum.length() == 15) {
            ai = idNum.substring(0, 6) + "19" + idNum.substring(6, 15);
        }
        String strYear = idNum.substring(6, 10);// 年份
        String strMonth = idNum.substring(10, 12);// 月份
        String strDay = idNum.substring(12, 14);// 日
        if (!DateUtil.isDate(strYear + "-" + strMonth + "-" + strDay)) {
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return false;
        }
        if (getAreaCode().get(ai.substring(0, 2)) == null) {
            return false;
        }
        int totalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            totalmulAiWi = totalmulAiWi + Integer.parseInt(String.valueOf(ai.charAt(i))) * Integer.parseInt(wi[i]);
        }
        int modValue = totalmulAiWi % 11;
        String strVerifyCode = valCodeArr[modValue];
        ai = ai + strVerifyCode;
        if (idNum.length() == 18) {
            if (ai.equalsIgnoreCase(idNum) == false) {
                return false;
            }
        }
        return true;

    }

    /**
     * 是否是中文字符
     *
     * @param character
     *         字符串参数
     *
     * @return
     */
    public static boolean isChineseCharacter(String character) {
        if (Check.NuNStr(character))
            return false;
        return character.matches(CHINESE);
    }

    /**
     * 是否护照
     *
     * @param passport
     *         护照编号
     *
     * @return
     */
    public static boolean isPassportNum(String passport) {
        if (Check.NuNStr(passport))
            return false;
        return passport.matches(PASSPORT);
    }

    /**
     * 是否港澳台胞证
     *
     * @param passport
     *         港澳台胞证号码
     *
     * @return
     */
    public static boolean isHMT(String passport) {
        if (Check.NuNStr(passport))
            return false;
        return passport.matches(PASSPORT);
    }

    /**
     * 是否军官证
     *
     * @param jg
     *         军官证编号
     *
     * @return
     */
    public static boolean isJG(String jg) {
        if (Check.NuNStr(jg))
            return false;
        return jg.matches(JG);
    }

    private static Hashtable getAreaCode() {
        if (!AREACODE_HASHTABLE.isEmpty()) {
            return AREACODE_HASHTABLE;
        }
        AREACODE_HASHTABLE.put("11", "北京");
        AREACODE_HASHTABLE.put("12", "天津");
        AREACODE_HASHTABLE.put("13", "河北");
        AREACODE_HASHTABLE.put("14", "山西");
        AREACODE_HASHTABLE.put("15", "内蒙古");
        AREACODE_HASHTABLE.put("21", "辽宁");
        AREACODE_HASHTABLE.put("22", "吉林");
        AREACODE_HASHTABLE.put("23", "黑龙江");
        AREACODE_HASHTABLE.put("31", "上海");
        AREACODE_HASHTABLE.put("32", "江苏");
        AREACODE_HASHTABLE.put("33", "浙江");
        AREACODE_HASHTABLE.put("34", "安徽");
        AREACODE_HASHTABLE.put("35", "福建");
        AREACODE_HASHTABLE.put("36", "江西");
        AREACODE_HASHTABLE.put("37", "山东");
        AREACODE_HASHTABLE.put("41", "河南");
        AREACODE_HASHTABLE.put("42", "湖北");
        AREACODE_HASHTABLE.put("43", "湖南");
        AREACODE_HASHTABLE.put("44", "广东");
        AREACODE_HASHTABLE.put("45", "广西");
        AREACODE_HASHTABLE.put("46", "海南");
        AREACODE_HASHTABLE.put("50", "重庆");
        AREACODE_HASHTABLE.put("51", "四川");
        AREACODE_HASHTABLE.put("52", "贵州");
        AREACODE_HASHTABLE.put("53", "云南");
        AREACODE_HASHTABLE.put("54", "西藏");
        AREACODE_HASHTABLE.put("61", "陕西");
        AREACODE_HASHTABLE.put("62", "甘肃");
        AREACODE_HASHTABLE.put("63", "青海");
        AREACODE_HASHTABLE.put("64", "宁夏");
        AREACODE_HASHTABLE.put("65", "新疆");
        AREACODE_HASHTABLE.put("71", "台湾");
        AREACODE_HASHTABLE.put("81", "香港");
        AREACODE_HASHTABLE.put("82", "澳门");
        AREACODE_HASHTABLE.put("91", "国外");
        return AREACODE_HASHTABLE;
    }
}
