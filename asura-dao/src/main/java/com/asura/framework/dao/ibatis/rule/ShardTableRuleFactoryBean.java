/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ShardTableRuleFactoryBean 
 * @package com.asura.framework.dao.ibatis.rule 
 *
 * @date 2015/4/3 10:23 
 */
package com.asura.framework.dao.ibatis.rule;

import com.asura.framework.dao.ibatis.strategy.IStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 分表策略FactoryBean </P>
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
public class ShardTableRuleFactoryBean implements FactoryBean<ShardTableRule>, InitializingBean {

    private final String SPLIT_TABLE = ",";
    private final String SPLIT_NS = ".";
    private ShardTableRule shardTableRule;
    private Resource ruleConfige; //分表配置文件

    @Override
    public ShardTableRule getObject() throws Exception {
        return shardTableRule;
    }

    @Override
    public Class<ShardTableRule> getObjectType() {
        return ShardTableRule.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        shardTableRule = new ShardTableRule(loadRules());
    }

    /**
     * @return the ruleConfige
     */
    public Resource getRuleConfige() {
        return ruleConfige;
    }

    /**
     * @param ruleConfige
     *         the ruleConfige to set
     */
    public void setRuleConfige(final Resource ruleConfige) {
        this.ruleConfige = ruleConfige;
    }

    private void makeRouterMap(final Entity entity, final Rule router, final Map<String, Rule> map) {
        for (final String sqlid : entity.sqlid.split(SPLIT_TABLE)) {
            final String key = entity.namespace.trim() + SPLIT_NS + sqlid.trim();
            map.put(key, router);
        }
    }

    /**
     * 加载配置文件
     *
     * @return
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Rule> loadRules() throws Exception {

        final Map<String, Rule> routerMap = new HashMap<String, Rule>();
        if (getRuleConfige() != null) {
            final XStream xstream = new XStream(new DomDriver());
            xstream.alias("rules", List.class);
            xstream.alias("rule", Entity.class);
            List<Entity> rules = new ArrayList<Entity>();
            rules = (List<Entity>) xstream.fromXML(getRuleConfige().getInputStream());

            for (final Entity entity : rules) {
                final Rule router = new Rule();
                final Class<?> clazz = Class.forName(entity.strategy);
                final IStrategy instance = (IStrategy) clazz.newInstance();
                instance.initStrategy(entity.tables.split(SPLIT_TABLE));
                router.setShardStrategy(instance);
                router.setXmlTableParam(entity.tableParam);
                router.setFieldParam(entity.field);
                makeRouterMap(entity, router, routerMap);

            }
        }
        return routerMap;
    }

    private class Entity {

        private String namespace; //orm文件 namespace
        private String id;
        private String sqlid;
        private String tableParam; //orm文件中配置的表变量名称
        private String field; //orm文件中配置的分表字段变量名称
        private String strategy;
        private String tables;

        @Override
        public String toString() {
            return "id:" + id + ",ns:" + namespace + ",sqlid:" + sqlid + ",field:" + field + ",strategy:" + strategy + ",tables:" + tables + ",tableParam:" + tableParam;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(final String[] args) {
        final XStream xstream = new XStream(new DomDriver());
        xstream.alias("routers", List.class);
        xstream.alias("router", Entity.class);
        List<Entity> rules = new ArrayList<Entity>();
        final File file = new File("E:\\eclipseworkspace\\SFbestFramework\\src\\test\\resources\\spring\\routers.xml");

        rules = (List<Entity>) xstream.fromXML(file);
        System.out.println(rules);
    }
}
