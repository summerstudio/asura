/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ThresholdFilter 
 * @package com.asura.framework.logback.filter 
 *
 * @date 2015/3/30 15:39 
 */
package com.asura.framework.logback.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * <p> 临界值过滤器 </P>
 * <p>
 * 默认情况下, 过滤掉低于指定临界值的事件. 当记录的级别等于或高于临界值时, 日志被记录. 当低于临界值时, 事件被拒绝, 日志被抛弃.
 * </p>
 * <p>
 * 通过配置rebel(叛逆的)属性, 过滤掉高于指定临界值的事件. 当记录的级别低于或等于临界值时,日志被记录. 当高于临界值时,事件被拒绝, 日志被抛弃
 * </p>
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
public class ThresholdFilter extends Filter<ILoggingEvent> {

    /**
     * 日志级别(临界值)
     */
    private Level level = Level.WARN;

    /**
     * 叛逆的
     */
    private boolean rebel;

    @Override
    public FilterReply decide(final ILoggingEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }

        if (rebel) {
            if (event.getLevel().isGreaterOrEqual(level)) {
                return FilterReply.DENY;
            }
            return FilterReply.NEUTRAL;
        }
        if (event.getLevel().isGreaterOrEqual(level)) {
            return FilterReply.NEUTRAL;
        }
        return FilterReply.DENY;
    }

    public void setLevel(final String level) {
        this.level = Level.toLevel(level);
    }

    @Override
    public void start() {
        if (this.level != null) {
            super.start();
        }
    }

    public boolean isRebel() {
        return rebel;
    }

    public void setRebel(final boolean rebel) {
        this.rebel = rebel;
    }
}
