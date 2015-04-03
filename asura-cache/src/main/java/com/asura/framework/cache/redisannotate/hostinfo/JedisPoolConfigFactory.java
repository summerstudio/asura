/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file JedisPoolConfigFactory 
 * @package com.asura.framework.cache.redisannotate.hostinfo 
 *
 * @date 2015/3/23 16:57 
 */
package com.asura.framework.cache.redisannotate.hostinfo;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p> 提供JedisPoolConfig配置解析工作 </P>
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
public class JedisPoolConfigFactory {

    private final static int MAX_ACTIVE = 2000;

    private final static int MAX_IDLE = 1;

    private final static int MAX_WAIT_TIME = 2000;

    public static JedisPoolConfig createJedisPoolConfig(int maxActive, int maxIdle, int maxWaitTime) {
        final JedisPoolConfig config = new JedisPoolConfig();
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(maxIdle == 0 ? MAX_IDLE : maxIdle);
        config.setMaxTotal(maxActive == 0 ? MAX_ACTIVE : maxActive);
        config.setMaxWaitMillis(maxWaitTime == 0 ? MAX_WAIT_TIME : maxWaitTime);
        return config;
    }

    private JedisPoolConfigFactory() {
        throw new AssertionError("Uninstantiable class");
    }
}
