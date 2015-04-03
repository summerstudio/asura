/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file DivisionHashShardStrategy 
 * @package com.asura.framework.dao.ibatis.strategy 
 *
 * @date 2015/4/3 10:08 
 */
package com.asura.framework.dao.ibatis.strategy;

/**
 * <p> 取余数分表策略 </P>
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
public class DivisionHashShardStrategy extends ShardStrategy {

    private int divisor = 3;

    public DivisionHashShardStrategy() {
        super();
    }

    public DivisionHashShardStrategy(String strategyName) {
        super(strategyName);
    }

    public DivisionHashShardStrategy(String strategyName, String[] shardTableArray) {
        super(strategyName, shardTableArray);
        divisor = shardTableArray.length;
    }

    public DivisionHashShardStrategy(String strategyName, String[] shardTableArray, int divisor) {
        super(strategyName, shardTableArray);
        this.divisor = divisor;
    }


    public void initStrategy(String[] shardTableArray) {
        this.shardTableArray = shardTableArray;
        divisor = shardTableArray.length;
    }

    @Override
    public String getTargetTable(Object paramValue) {
        int offset = 0;
        if (paramValue instanceof Long) {
            offset = Long.bitCount((Long) paramValue % divisor);
        } else if (paramValue instanceof Integer) {
            offset = (Integer) paramValue % divisor;
        } else {
            offset = paramValue.hashCode() % divisor;
        }
        return shardTableArray[offset];
    }

    public String toString() {
        return "strategyName:" + strategyName + ",divisor:" + divisor + ",shardTableArray:" + shardTableArray + strategyName;
    }
}
