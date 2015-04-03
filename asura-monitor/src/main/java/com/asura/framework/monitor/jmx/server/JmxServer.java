/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file JmxServer 
 * @package com.asura.framework.monitor.jmx.server 
 *
 * @date 2015/3/31 11:19 
 */
package com.asura.framework.monitor.jmx.server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asura.framework.base.exception.BusinessException;

/**
 * <p> JMX服务 </P>
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
public class JmxServer {

    private Logger logger = LoggerFactory.getLogger(JmxServer.class);

    /**
     * MBeanSrver端口
     */
    private int port = 9020;

    /**
     * 实例化JmxServer
     */
    private static JmxServer instance = new JmxServer();

    /**
     * 自定义JMXConnectorServer
     */
    private JMXConnectorServer connectorServer;

    /**
     * 自定义MBeanServer
     */
    private MBeanServer server;

    /**
     * 私有构造器，防止外部实例化
     */
    private JmxServer() {
        start();
    }

    /**
     * 获取JmxServer实例
     *
     * @return JmxServer实例
     */
    public static JmxServer getInstance() {
        return instance;
    }

    /**
     * 在当前Server上创建MBean
     *
     * @param className
     *         类名
     * @param mbeanName
     *         MBean名称
     *
     * @return 创建成功标识
     *
     * @throws BusinessException
     *         创建MBean出错时抛出异常
     */
    public boolean registMBean(String className, String mbeanName) throws BusinessException {
        ObjectInstance instance = null;
        ObjectName objMBeanName = null;
        try {
            objMBeanName = new ObjectName(mbeanName);
        } catch (MalformedObjectNameException e) {
            logger.error("创建MBeanName异常。", e.getCause());
            throw new BusinessException(e.getMessage(), e.getCause());
        }
        try {
            instance = server.createMBean(className, objMBeanName);
        } catch (InstanceAlreadyExistsException e) {
            logger.error("创建MBean异常，实例已经存在。", e.getCause());
            throw new BusinessException("创建MBean异常，实例已经存在。", e.getCause());
        } catch (NotCompliantMBeanException e) {
            logger.error("创建MBean异常，JMX不兼容的MBean类型。", e.getCause());
            throw new BusinessException("创建MBean异常，JMX不兼容的MBean类型。", e.getCause());
        } catch (MBeanRegistrationException e) {
            logger.error("注册MBean异常。", e.getCause());
            throw new BusinessException("注册MBean异常。", e.getCause());
        } catch (ReflectionException e) {
            logger.error("创建MBean调用java.lang.reflect异常。", e.getCause());
            throw new BusinessException("创建MBean调用java.lang.reflect异常。", e.getCause());
        } catch (MBeanException e) {
            logger.error("创建MBean出现异常。", e.getCause());
            throw new BusinessException("创建MBean出现异常。", e.getCause());
        }
        if (null == instance) {
            return false;
        } else {
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ").format(new Date()) + "Registed mbean : " + className + ".");
            return instance.getClassName().equals(className);
        }
    }

    /**
     * 为指定的MBean添加NotificationListener
     *
     * @param mbeanName
     *         MBean名称
     * @param listener
     *         监听器
     *
     * @throws BusinessException
     *         MBean实例不存在时抛出异常
     */
    public void addNotificationListener(String mbeanName, NotificationListener listener) throws BusinessException {
        try {
            ObjectName objMBeanName = null;
            try {
                objMBeanName = new ObjectName(mbeanName);
            } catch (MalformedObjectNameException e) {
                e.printStackTrace();
                throw new BusinessException(e.getMessage(), e.getCause());
            }
            server.addNotificationListener(objMBeanName, listener, null, null);
        } catch (InstanceNotFoundException e) {
            logger.error("注册NotificationListener异常，Mbean实例不存在。", e.getCause());
            throw new BusinessException("注册NotificationListener异常，Mbean实例不存在。", e.getCause());
        }
    }

    /**
     * 启动JMXConnectorServer
     *
     * @throws IOException
     */
    private void start() {
        if (null != server)
            return;
        try {
            server = MBeanServerFactory.createMBeanServer("Asura");
            JMXServiceURL url = new JMXServiceURL("jmxmp", null, port);
            connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
            connectorServer.start();
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ").format(new Date()) + "JMX Server started! used port:" + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ").format(new Date()) + "JMX Server started failed!" + " " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * 停止JMXConnectorServer
     *
     * @throws IOException
     */
    public void stop() throws IOException {
        if (null != connectorServer) {
            connectorServer.stop();
        }
    }
}
