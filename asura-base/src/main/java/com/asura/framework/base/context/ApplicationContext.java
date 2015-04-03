/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ApplicationContext 
 * @package com.asura.framework.base.context 
 *
 * @date 2015/3/18 10:04 
 */
package com.asura.framework.base.context;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.container.spring.SpringContainer;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * <p> Spring请求上下文信息 </P>
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
public class ApplicationContext {

    /**
     * 获取Spring上下文信息
     *
     * @return
     */
    public static AbstractApplicationContext getContext() {
        return SpringContainer.getContext();
    }

    /**
     * 获取当前应用名称
     *
     *  @return 应用名称
     */
    public static String getApplicationName() {
        return ConfigUtils.getProperty("dubbo.application.name");
    }

    /**
     * 获取注册中心地址
     *
     * @return 注册中心地址
     */
    public static String getRegistryAddress() {
        return ConfigUtils.getProperty("dubbo.registry.address");
    }

    /**
     * 当前应用是否为服务提供者
     *
     * @return 当前应用是否为服务提供者
     */
    public static boolean isProviderSide() {
        return getApplicationName().endsWith("Provider");
    }

    /**
     * 当前应用是否为服务消费者
     *
     * @return 当前应用是否为服务消费者
     */
    public static boolean isConsumerSide() {
        return getApplicationName().endsWith("Consumer");
    }
}
