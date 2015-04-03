/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file HostInfoFactory 
 * @package com.asura.framework.cache.redisannotate.hostinfo 
 *
 * @date 2015/3/23 17:14 
 */
package com.asura.framework.cache.redisannotate.hostinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p> redis服务器信息解析类 </P>
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
public class HostInfoFactory {

    /**
     * 主分割符
     */
    private static final String SPLITER = "|";

    /**
     * 子分割符
     */
    private static final String SUB_SPLITER = ":";

    public static final HostInfo[] split(final String host_port_timeout) {
        final String[] host_port_timeouts = host_port_timeout.split(SPLITER);

        final List<HostInfo> hostInfos = new ArrayList<HostInfo>();
        for (final String hpts : host_port_timeouts) {
            final String[] hpt = hpts.split(SUB_SPLITER);
            final List<String> hosts = Arrays.asList(hpt);

            final HostInfo hostInfo = HostInfo.createHostInfo(hosts);
            hostInfos.add(hostInfo);
        }
        return hostInfos.toArray(new HostInfo[0]);
    }

    private HostInfoFactory() {
        throw new AssertionError("Uninstantiable class");
    }
}
