/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file InitSubscriber 
 * @package com.asura.framework.conf.subscribe 
 *
 * @date 2015/3/24 15:30 
 */
package com.asura.framework.conf.subscribe;

import org.springframework.beans.factory.InitializingBean;

/**
 * <p> 初始化消息订阅者 </P>
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
public class InitSubscriber implements InitializingBean {

    private InitSubscriber() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ConfigSubscriber.getInstance();
        // 必须在ConfigSubscriber.getInstance()之后
        AsuraSubAnnotationProcessor.getInstance();
    }
}
