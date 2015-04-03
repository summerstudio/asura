/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file InitPublisher 
 * @package com.asura.framework.conf.publish 
 *
 * @date 2015/3/24 9:49 
 */
package com.asura.framework.conf.publish;

import org.springframework.beans.factory.InitializingBean;

/**
 * <p> 初始化信息发布者 </P>
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
public class InitPublisher implements InitializingBean {

    private InitPublisher() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ConfigPublisher.getInstance();
    }
}
