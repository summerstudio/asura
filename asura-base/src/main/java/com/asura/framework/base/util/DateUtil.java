/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file DateUtil 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/18 10:51 
 */
package com.asura.framework.base.util;

import com.asura.framework.base.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> 日期工具类 </P>
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
public class DateUtil {

    public enum IntervalUnit {
        MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR
    }

    private static final Map<String, ThreadLocal<SimpleDateFormat>> timestampFormatPool = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    private static final Map<String, ThreadLocal<SimpleDateFormat>> dateFormatPool = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    private static final Object timestampFormatLock = new Object();

    private static final Object dateFormatLock = new Object();

    private static String dateFormatPattern = "yyyy-MM-dd";

    private static String timestampPattern = "yyyy-MM-dd HH:mm:ss";

    private static SimpleDateFormat getDateFormat() {
        ThreadLocal<SimpleDateFormat> tl = dateFormatPool.get(dateFormatPattern);
        if (null == tl) {
            synchronized (dateFormatLock) {
                tl = dateFormatPool.get(dateFormatPattern);
                if (null == tl) {
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected synchronized SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(dateFormatPattern);
                        }
                    };
                    dateFormatPool.put(dateFormatPattern, tl);
                }
            }
        }
        return tl.get();
    }

    private static SimpleDateFormat getTimestampFormat() {
        ThreadLocal<SimpleDateFormat> tl = timestampFormatPool.get(timestampPattern);
        if (null == tl) {
            synchronized (timestampFormatLock) {
                tl = timestampFormatPool.get(timestampPattern);
                if (null == tl) {
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected synchronized SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(timestampPattern);
                        }
                    };
                    timestampFormatPool.put(timestampPattern, tl);
                }
            }
        }
        return tl.get();
    }

    /**
     * 格式化成时间戳格式
     *
     * @param date
     *         要格式化的日期
     *
     * @return "年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
     */
    public static String timestampFormat(final Date date) {
        if (date == null) {
            return "";
        }
        return getTimestampFormat().format(date);
    }

    /**
     * 格式化成时间戳格式
     *
     * @param datetime
     *         要格式化的日期
     *
     * @return "年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
     */
    public static String timestampFormat(final long datetime) {
        return getTimestampFormat().format(new Date(datetime));
    }

    /**
     * 将"年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串转换成Long型日期
     *
     * @param timestampStr
     *         年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
     *
     * @return Long型日期
     */
    public static long formatTimestampToLong(final String timestampStr) {
        Date date;
        try {
            date = getTimestampFormat().parse(timestampStr);
        } catch (final ParseException e) {
            return 0L;
        }
        return date.getTime();
    }

    /**
     * 格式化成日期格式
     *
     * @param date
     *         要格式化的日期
     *
     * @return "年年年年-月月-日日"格式的日期字符串
     */
    public static String dateFormat(final Date date) {
        if (date == null) {
            return "";
        }
        return getDateFormat().format(date);
    }

    /**
     * 格式化成日期格式
     *
     * @param datetime
     *         要格式化的日期
     *
     * @return "年年年年-月月-日日"格式的日期字符串
     */
    public static String dateFormat(final long datetime) {
        return getDateFormat().format(new Date(datetime));
    }

    /**
     * 将"年年年年-月月-日日"格式的日期字符串转换成Long型日期
     *
     * @param dateStr
     *         "年年年年-月月-日日"格式的日期字符串
     *
     * @return Long型日期
     *
     * @throws BusinessException
     *         日期格式化异常
     */
    public static long formatDateToLong(final String dateStr) throws BusinessException {
        Date date;
        try {
            date = getDateFormat().parse(dateStr);
        } catch (final ParseException e) {
            throw new BusinessException("将输入内容格式化成日期类型时出错。", e);
        }
        return date.getTime();
    }

    /**
     * 得到本月的第一天
     *
     * @return 以"年年年年-月月-日日"格式返回当前月第一天的日期
     */
    public static String getFirstDayOfCurrentMonth() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return getDateFormat().format(calendar.getTime());
    }

    /**
     * 得到月份第一天.以当前月份为基准
     *
     * @param offset
     *
     * @return Date
     */
    public static Date getFirstDayOfMonth(int offset) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, offset);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 得到下个月的第一秒
     *
     * @return
     */
    public static Long getFirstMillSecondOfNextMonth() {
        Date d = getFirstDayOfMonth(1);
        return d.getTime();
    }

    /**
     * 得到下个月的最后一秒
     *
     * @return
     */
    public static Long getLastMillSecondOfNextMonth() {
        Date d = getLastDayOfMonth(1);
        return d.getTime() + 24 * 3600 * 1000L - 1000L;
    }

    /**
     * 得到月份最后一天.以当前月份为基准
     *
     * @param offset
     *         偏移量
     *
     * @return 以"年年年年-月月-日日"格式返回当前月最后一天的日期
     */
    public static Date getLastDayOfMonth(int offset) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, offset);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到本月的最后一天
     *
     * @return 以"年年年年-月月-日日"格式返回当前月最后一天的日期
     */
    public static String getLastDayOfCurrentMonth() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDateFormat().format(calendar.getTime());
    }

    /**
     * 获取指定日期所在月的第一天
     *
     * @param date
     *         日期
     *
     * @return 以"年年年年-月月-日日"格式返回当指定月第一天的日期
     */
    public static String getFirstDayOfMonth(final Date date) {
        final Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return getDateFormat().format(ca.getTime());
    }

    /**
     * 获取指定日期所在月的最后一天
     *
     * @param date
     *
     * @return 以"年年年年-月月-日日"格式返回当指定月最后一天的日期
     */
    public static String getLastDayOfMonth(final Date date) {
        final Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.roll(Calendar.DAY_OF_MONTH, -1);
        return getDateFormat().format(ca.getTime());
    }

    /**
     * 获取指定日期所在月的最后一天
     *
     * @param date
     *
     * @return 以"年年年年-月月-日日"格式返回当指定月最后一天的日期
     */
    public static Long getLastDayTimeOfMonth(final Date date) {
        final Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.roll(Calendar.DAY_OF_MONTH, -1);
        return ca.getTime().getTime();
    }

    /**
     * 获取指定日期所在周的第一天
     *
     * @param date
     *         日期
     *
     * @return 以"年年年年-月月-日日"格式返回当指定周第一天的日期
     */
    public static String getFirstDayOfWeek(final Date date) {
        final Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_WEEK, 2);
        return getDateFormat().format(ca.getTime());
    }

    /**
     * 获取当前日期所在周的周末
     *
     * @param date
     *         日期
     *
     * @return 当前日期所在周的周末
     */
    private static Calendar lastDayOfWeek(final Date date) {
        final Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        //日期减去1防止是周日（国外周日为一周的第一天）
        ca.add(Calendar.DATE, -1);
        //设置为本周的周六,这里不能直接设置为周日，中国本周日和国外本周日不同
        ca.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        //日期往前推移一天
        ca.add(Calendar.DATE, 1);
        return ca;
    }

    /**
     * 获取当前日期所在周的周末
     *
     * @param date
     *         日期
     *
     * @return 以"年年年年-月月-日日"
     */
    public static String getLastDayOfWeek(final Date date) {
        final Calendar ca = lastDayOfWeek(date);
        return getDateFormat().format(ca.getTime());
    }

    /**
     * 获取当前日期所在的周六    第一秒
     *
     * @param date
     *         日期
     *
     * @return
     */
    public static Long getSaturdayOfWeek(final Date date) {
        //周日零点零分零秒
        final Calendar ca = lastDayOfWeek(date);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        //日期减去1天 变为周六零时零分零秒
        ca.add(Calendar.DATE, -1);
        return ca.getTime().getTime();
    }

    /**
     * 获取当前日期所在的周日    最后一秒
     *
     * @param date
     *         日期
     *
     * @return
     */
    public static Long getLastSecondOfWeek(final Date date) {
        final Calendar ca = lastDayOfWeek(date);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        ca.add(Calendar.DATE, 1);
        return ca.getTime().getTime() - 1;
    }

    /**
     * 获取当前日期的前一天
     *
     * @return 以"年年年年-月月-日日"格式返回当前日期的前一天的日期
     */
    public static String getDayBeforeCurrentDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return getDateFormat().format(calendar.getTime());
    }

    /**
     * 获取指定日期的前一天
     *
     * @param date
     *
     * @return 以"年年年年-月月-日日"格式返回指定日期的前一天的日期
     */
    public static String getDayBeforeDate(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return getDateFormat().format(calendar.getTime());
    }

    /**
     * 获取当前日期的后一天
     *
     * @return 以"年年年年-月月-日日"格式返回当前日期的后一天的日期
     */
    public static String getDayAfterCurrentDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return getDateFormat().format(calendar.getTime());
    }

    /**
     * 获取当前日期的后一天
     *
     * @return 以"年年年年-月月-日日"格式返回指定日期的后一天的日期
     */
    public static String getDayAfterDate(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return getDateFormat().format(calendar.getTime());
    }

    /**
     * 获取当前时间，精确到秒
     *
     * @return 精确到秒的当前时间
     */
    public static int currentTimeSecond() {
        return Long.valueOf(System.currentTimeMillis() / 1000).intValue();
    }

    /**
     * 替换掉日期格式中所有分隔符（含“-”、“:”及空格）
     *
     * @param target
     *         字符型目标日期
     *
     * @return 替换后的结果
     */
    public static String replaceAllSeparator(final String target) {
        return target.replace("-", "").replace(":", "").replace(" ", "");
    }

    /**
     * 替换掉日志格式中指定的分隔符
     *
     * @param target
     *         字符型目标日期
     * @param separator
     *         要被替换掉的分割符
     *
     * @return 替换后的结果
     */
    public static String replaceSeparator(final String target, final String... separator) {
        String temp = target;
        for (final String sep : separator) {
            temp = temp.replace(sep, "");
        }
        return temp;
    }

    /**
     * 根据步长获取时间
     *
     * @param interval
     *         步长 ，正数获取将来时间， 负数获取以前的时间
     * @param unit
     *         步长单位, 年月周日时分秒
     *
     * @return 时间
     */
    public static Date intervalDate(final int interval, final IntervalUnit unit) {
        final Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.setLenient(true);
        c.add(translate(unit), interval);
        return c.getTime();
    }

    private static int translate(final IntervalUnit unit) {
        switch (unit) {
            case DAY:
                return Calendar.DAY_OF_YEAR;
            case HOUR:
                return Calendar.HOUR_OF_DAY;
            case MINUTE:
                return Calendar.MINUTE;
            case MONTH:
                return Calendar.MONTH;
            case SECOND:
                return Calendar.SECOND;
            case MILLISECOND:
                return Calendar.MILLISECOND;
            case WEEK:
                return Calendar.WEEK_OF_YEAR;
            case YEAR:
                return Calendar.YEAR;
            default:
                throw new IllegalArgumentException("Unknown IntervalUnit");
        }
    }

    /**
     * 获取几天前或几天后的日期
     *
     * @param day
     *         可为负数,为负数时代表获取之前的日期.为正数,代表获取之后的日期
     *
     * @return 当前日期几天前或几天后的日期
     */
    public static Date getTime(final int day) {
        return getTime(new Date(), day);
    }

    /**
     * 获取指定日期几天前或几天后的日期
     *
     * @param date
     *         指定的日期
     * @param day
     *         可为负数, 为负数时代表获取之前的日志.为正数,代表获取之后的日期
     *
     * @return 指定日期几天前或几天后的日期
     */
    public static Date getTime(final Date date, final int day) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        return calendar.getTime();
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     *         日期字符串
     *
     * @return true：是日期格式 false：不是日期格式
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
