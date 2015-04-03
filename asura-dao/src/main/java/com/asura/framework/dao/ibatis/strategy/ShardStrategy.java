/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ShardStrategy 
 * @package com.asura.framework.dao.ibatis.strategy 
 *
 * @date 2015/4/3 10:00 
 */
package com.asura.framework.dao.ibatis.strategy;

/**
 * <p> 分表策略接口基类 </P>
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
public abstract class ShardStrategy implements IStrategy {

    /**
     * 策略名称
     */
    protected String strategyName;

    /**
     * 分表数组
     */
    protected String[] shardTableArray;

    public ShardStrategy() {
        this.strategyName = "NULL_NAME_STRATEGY";
    }

    public ShardStrategy(String strategyName) {
        this.strategyName = strategyName;
    }

    public ShardStrategy(String strategyName, String[] shardTableArray) {
        this.strategyName = strategyName;
        this.shardTableArray = shardTableArray;
    }
}
