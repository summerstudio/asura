/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file IStrategy 
 * @package com.asura.framework.dao.ibatis.strategy 
 *
 * @date 2015/4/3 9:59 
 */
package com.asura.framework.dao.ibatis.strategy;

/**
 * <p> 策略接口 </P>
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
public interface IStrategy {

    public String getTargetTable(Object paramValue);

    public void initStrategy(String[] shardTableArray);
}
