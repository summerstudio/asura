/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file RequestContext 
 * @package com.asura.framework.base.context 
 *
 * @date 2015/3/18 9:44 
 */
package com.asura.framework.base.context;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * <p> 请求的上下文信息 </P>
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
public class RequestContext {

    private static RequestContext ourInstance = new RequestContext();

    private RequestContext() {
    }

    /**
     * 获得请求上下文对象
     *
     * @return 请求上下文对象
     */
    public static RequestContext getInstance() {
        return ourInstance;
    }

    /**
     * 获取请求者地址
     *
     * @return 请求者地址
     */
    public String getRemoteAddress() {
        return RpcContext.getContext().getRemoteHost();
    }

    /**
     * 获取请求者端口
     *
     * @return 请求者端口
     */
    public int getRemotePort() {
        return RpcContext.getContext().getRemotePort();
    }

    /**
     * 获取本地地址
     *
     * @return 本地地址
     */
    public String getLocalAddress() {
        return RpcContext.getContext().getLocalHost();
    }

    /**
     * 获取本地端口
     *
     * @return 本地端口
     */
    public int getLocalPort() {
        return RpcContext.getContext().getLocalPort();
    }
}
