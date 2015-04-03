/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ApplicationNode 
 * @package com.asura.framework.monitor.entity 
 *
 * @date 2015/3/31 9:15 
 */
package com.asura.framework.monitor.entity;

import java.io.Serializable;

/**
 * <p> 应用节点信息 </P>
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
public class ApplicationNode implements Serializable {

    private static final long serialVersionUID = -3131593602232982049L;

    /**
     * 节点IP
     */
    private String ip;

    /**
     * 节点应用名称
     */
    private String applicationName;

    /**
     * 节点存活状态
     */
    private boolean alive = true;

    /**
     * 节点类型
     */
    private String side;

    /**
     * 构造器
     */
    public ApplicationNode() {
    }

    /**
     * 构造器
     *
     * @param ip
     *         节点IP
     * @param applicationName
     *         节点应用名称
     */
    public ApplicationNode(String ip, String applicationName, String side) {
        this.ip = ip;
        this.applicationName = applicationName;
        this.side = side;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *         the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param applicationName
     *         the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive
     *         the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * @return the side
     */
    public String getSide() {
        return side;
    }

    /**
     * @param side
     *         the side to set
     */
    public void setSide(String side) {
        this.side = side;
    }
}
