/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ApplicationNodeListener 
 * @package com.asura.framework.monitor.listener 
 *
 * @date 2015/3/31 9:17 
 */
package com.asura.framework.monitor.listener;

import com.asura.framework.monitor.entity.ApplicationNode;

import java.util.List;

/**
 * <p> 被监控应用在ZKServer上注册节点信息 </P>
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
public interface ApplicationNodeListener {

    /**
     * 节点变化
     *
     * @param applicationNodes
     *         节点信息列表
     */
    public void nodeChange(List<ApplicationNode> applicationNodes);

}
