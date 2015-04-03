/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file JmxClient 
 * @package com.asura.framework.monitor.jmx.client 
 *
 * @date 2015/3/31 11:10 
 */
package com.asura.framework.monitor.jmx.client;

import com.asura.framework.base.exception.BusinessException;
import com.asura.framework.monitor.jmx.mbean.CommandMBean;
import com.asura.framework.monitor.jmx.mbean.SystemInfoMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * <p>JMX客户端，每调用一次newInstance方法就会创建一个新的实例，因此需要在使用结束后调用destroy方法进行销毁，参考代码结构如下：</p>
 * <p>
 * private JmxClient jmxClient = JmxClient.newInstance();
 *
 * ...........
 * jmxClient.getSystemInfo(ip);
 * ...........
 *
 * jmxClient.destroy();
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
public class JmxClient {

    private Logger logger = LoggerFactory.getLogger(JmxClient.class);

    /**
     * JMX Client与server的连接器
     */
    private JMXConnector jmxc = null;

    /**
     * 私有构造器，防止外部实例化
     */
    private JmxClient() {
    }

    /**
     * 创建全新的JmxClient实例
     *
     * @return JmxClient实例
     */
    public static JmxClient newInstance() {
        return new JmxClient();
    }

    /**
     * 获取应用的系统信息，含软、硬件信息，以软件信息为主
     *
     * @param applicationIp
     *         应用的IP
     *
     * @return 目标应用的系统信息
     *
     * @throws BusinessException
     *         创建连接、获取数据过程中可能出现的异常，如创建JMX连接等
     */
    public SystemInfoMBean getSystemInfo(String applicationIp) throws BusinessException {
        return getCustomMBean(SystemInfoMBean.class, SystemInfoMBean.SYSTEMINFO_MXBEAN_NAME, applicationIp);
    }

    /**
     * 获取远程操作对象
     *
     * @param applicationIp
     *         应用的IP
     *
     * @return 远程操作对象
     */
    public CommandMBean getCommand(String applicationIp) {
        return getCustomMBean(CommandMBean.class, CommandMBean.COMMAND_MXBEAN_NAME, applicationIp);
    }

    /**
     * 获得指定类型的MBean
     *
     * @param clazz
     *         Class类型
     * @param objectName
     *         名称
     * @param applicationIp
     *         应用IP
     *
     * @return MBean实现
     *
     * @throws BusinessException
     */
    public <T> T getCustomMBean(Class<T> clazz, String objectName, String applicationIp) throws BusinessException {
        try {
            JMXServiceURL url = new JMXServiceURL("jmxmp", applicationIp, 9020);
            jmxc = JMXConnectorFactory.connect(url, null);
            return MBeanServerInvocationHandler.newProxyInstance(jmxc.getMBeanServerConnection(), new ObjectName(objectName), clazz, false);
        } catch (MalformedURLException e) {
            logger.error("创建JMX连接异常，URL格式不正确。", e.getCause());
            throw new BusinessException("创建JMX连接异常，URL格式不正确。", e.getCause());
        } catch (IOException e) {
            logger.error("连接JMXServer异常。", e.getCause());
            throw new BusinessException("连接JMXServer异常。", e.getCause());
        } catch (MalformedObjectNameException e) {
            logger.error("创建MBeanName异常。", e.getCause());
            throw new BusinessException("创建MBeanName异常。", e.getCause());
        }
    }

    /**
     * 销毁JMXConnector对象，关闭与JMXserver的连接
     */
    public void destroy() {
        try {
            jmxc.close();
        } catch (IOException e) {
            logger.error("关闭JMXConnector异常。", e.getCause());
            throw new BusinessException("关闭JMXConnector异常。", e.getCause());
        }
    }
}
