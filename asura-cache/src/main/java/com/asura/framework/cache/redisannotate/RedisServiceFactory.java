/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file RedisServiceFactory 
 * @package com.asura.framework.cache.redisannotate 
 *
 * @date 2015/3/23 16:42 
 */
package com.asura.framework.cache.redisannotate;

import com.asura.framework.cache.redisannotate.exception.RedisException;
import com.asura.framework.cache.redisannotate.hostinfo.HostInfo;
import com.asura.framework.cache.redisannotate.hostinfo.HostInfoFactory;
import com.asura.framework.cache.redisannotate.hostinfo.JedisPoolConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * <p> Redis服务工厂 </P>
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
@Service("redisServiceFactory")
public class RedisServiceFactory implements InitializingBean, FactoryBean<RedisService> {

    private final static Logger logger = LoggerFactory.getLogger(RedisServiceFactory.class);

    @Value("#{${redis.pool.maxActive}}")
    private static int MAX_ACTIVE;

    @Value("#{${redis.pool.maxIdle}}")
    private static int MAX_IDLE;

    @Value("#{${redis.maxWaitTime}}")
    private static int MAX_WAIT_TIME;

    @Value("#{${redis.servers}}")
    private static String servers;

    @Value("#{${redis.app}}")
    private static String app;

    private RedisService redisService;

    /**
     * @param redisService
     *         配置RedisService.
     *         提供用户自定义实现. 若用户未指定, 则采用默认服务实现RedisServiceImpl.
     */
    public void setRedisService(final RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            final JedisPoolConfig config = JedisPoolConfigFactory.createJedisPoolConfig(MAX_ACTIVE, MAX_IDLE, MAX_WAIT_TIME);

            final Set<JedisShardInfo> shardInfos = new HashSet<JedisShardInfo>();
            final HostInfo[] hostInfos = HostInfoFactory.split(servers);
            for (final HostInfo hostInfo : hostInfos) {
                shardInfos.add(hostInfo.createJedisShardInfo());
            }
            if (redisService == null) {
                final ShardedJedisPool jedisPool = new ShardedJedisPool(config, new ArrayList<JedisShardInfo>(shardInfos));
                redisService = new RedisServiceImpl(jedisPool);
            }

            final RedisServiceProxy redisServiceProxy = new RedisServiceProxy();
            this.redisService = redisServiceProxy.bind(redisService);
        } catch (RedisException e) {
            System.out.println("redis客户端初始化连接异常!!!!!!!!!  链接参数:" + servers + " " + MAX_WAIT_TIME + " " + app);
            logger.error("redis:{},exception:{}.", servers + " " + MAX_WAIT_TIME + " " + app, e);
        }
    }

    @Override
    public RedisService getObject() throws Exception {
        return redisService;
    }

    @Override
    public Class<?> getObjectType() {
        return RedisService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
