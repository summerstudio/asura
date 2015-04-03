/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ConfigSubscriber 
 * @package com.asura.framework.conf.subscribe 
 *
 * @date 2015/3/24 10:28 
 */
package com.asura.framework.conf.subscribe;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.asura.framework.base.util.Check;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p> 配置信息订阅 </P>
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
public class ConfigSubscriber {

    final static Logger logger = LoggerFactory.getLogger("ConfigPublisher.class");

    public final String zkDataPath = "/AsuraConfig";

    private ZkClient client;

    private static ConfigSubscriber sub;

    /**
     * 将系统用到的参数放入内存中
     */
    private ConcurrentMap<String, String> params;

    private ConfigSubscriber() {
        init();
    }

    /**
     * 获取ConfigSubscriber对象
     *
     * @return ConfigSubscriber实例
     */
    public static ConfigSubscriber getInstance() {
        if (null != sub) {
            return sub;
        }
        sub = new ConfigSubscriber();
        return sub;
    }

    /**
     * 初始化zk连接
     */
    private void init() {
        final String address = ConfigUtils.getProperty("dubbo.registry.address");
        final String applicationName = ConfigUtils.getProperty("dubbo.application.name");
        final int connectionTimeout = 60000;
        String zookeeperServer = null;
        if (address.startsWith("zookeeper") && address.length() > 20) {
            params = new ConcurrentHashMap<String, String>();
            zookeeperServer = address.replace("\\", "").replace("zookeeper://", "").replace("?backup=", ",");
            try {
                client = new ZkClient(zookeeperServer, connectionTimeout);
            } catch (final Exception e) {
                e.printStackTrace();
                logger.error("连接配置中心服务器超时，时间" + connectionTimeout + "毫秒。", e.getCause());
                System.exit(1);
            }
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ").format(new Date()) + applicationName + " connected to config server(" + zookeeperServer + ").");
            logger.info(applicationName + " connected to cofnig server(" + zookeeperServer + ").");
        }
    }

    /**
     * 注册配置的type、code，以保证数据在发生变化时能及时得到通知
     *
     * @param type
     *         配置信息类型
     * @param code
     *         配置信息编码
     */
    public void registConfig(final String type, final String code) {
        final String path = zkDataPath + "/" + type + "/" + code;
        if (client.exists(path)) {
            final String data = (String) client.readData(path);
            if (!Check.NuNStr(data)) {
                params.put(path, data);
            }
        }

        client.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataDeleted(final String path) throws Exception {
                params.remove(path);
            }

            @Override
            public void handleDataChange(final String path, final Object data) throws Exception {
                if (null == data) {
                    logger.info("类型" + type + " code" + code + " 对应的值不存在此数据或数据是空");
                }
                params.put(path, (String) data);
            }
        });
    }

    /**
     * 从zookeeper上获取配置信息
     *
     * @param type
     *         配置信息类型
     * @param code
     *         配置信息编码
     *
     * @return 配置信息值
     */
    public String getConfigValue(final String type, final String code) {
        final String path = zkDataPath + "/" + type + "/" + code;
        String data = params.get(path);
        if (Check.NuNStr(data)) {
            data = client.readData(path);
            if (!Check.NuNStr(data)) {
                params.put(path, data);
            }
        }
        return params.get(path);
    }
}
