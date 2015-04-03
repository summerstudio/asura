/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ShardTableRule 
 * @package com.asura.framework.dao.ibatis.rule 
 *
 * @date 2015/4/3 10:22 
 */
package com.asura.framework.dao.ibatis.rule;

import java.util.Map;

/**
 * <p> 分表策略对象 </P>
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
public class ShardTableRule {

    protected Map<String, Rule> routerMap;

    public ShardTableRule(Map<String, Rule> routerMap) {
        this.routerMap = routerMap;
    }

    public Map<String, Rule> getRouterMap() {
        return routerMap;
    }

    public void setRouterMap(Map<String, Rule> routerMap) {
        this.routerMap = routerMap;
    }
}
