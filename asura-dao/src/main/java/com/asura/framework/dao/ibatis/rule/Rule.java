/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file Rule 
 * @package com.asura.framework.dao.ibatis.rule 
 *
 * @date 2015/4/3 10:12 
 */
package com.asura.framework.dao.ibatis.rule;

import com.asura.framework.dao.ibatis.strategy.IStrategy;

/**
 * <p> 分表规则对象 </P>
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
public class Rule {

    private String ruleSqlId;
    private String xmlTableParam;    //orm文件中配置的表变量名称
    private String fieldParam;        //orm文件中配置的分表字段变量名称
    private IStrategy shardStrategy;

    /**
     * @return the shardStrategy
     */
    public IStrategy getShardStrategy() {
        return shardStrategy;
    }

    /**
     * @return the ruleSqlId
     */
    public String getRuleSqlId() {
        return ruleSqlId;
    }

    /**
     * @param ruleSqlId
     *         the ruleSqlId to set
     */
    public void setRuleSqlId(String ruleSqlId) {
        this.ruleSqlId = ruleSqlId;
    }

    /**
     * @param shardStrategy
     *         the shardStrategy to set
     */
    public void setShardStrategy(IStrategy shardStrategy) {
        this.shardStrategy = shardStrategy;
    }


    /**
     * @return the fieldParam
     */
    public String getFieldParam() {
        return fieldParam;
    }

    /**
     * @param fieldParam
     *         the fieldParam to set
     */
    public void setFieldParam(String fieldParam) {
        this.fieldParam = fieldParam;
    }

    /**
     * @return the xmlTableParam
     */
    public String getXmlTableParam() {
        return xmlTableParam;
    }

    /**
     * @param xmlTableParam
     *         the xmlTableParam to set
     */
    public void setXmlTableParam(String xmlTableParam) {
        this.xmlTableParam = xmlTableParam;
    }
}
