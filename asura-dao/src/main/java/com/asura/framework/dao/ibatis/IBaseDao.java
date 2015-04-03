/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file IBaseDao 
 * @package com.asura.framework.dao.ibatis 
 *
 * @date 2015/4/3 9:45 
 */
package com.asura.framework.dao.ibatis;

import com.asura.framework.base.entity.BaseEntity;
import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;

import java.util.List;
import java.util.Map;

/**
 * <p> ibatis基类 </P>
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
public interface IBaseDao {

    /**
     * 查询指定SQL语句的所有记录
     *
     * @param sqlId
     *         SQL语句ID
     *
     * @return 查询到的实体集合
     */
    public <T extends BaseEntity> List<T> findAll(String sqlId);

    /**
     * 查询指定SQL语句的所有记录
     *
     * @param sqlId
     *         SQL语句ID
     * @param searchModel
     *         查询模型
     *
     * @return 查询到的实体集合
     */
    public <T extends BaseEntity> List<T> findAll(String sqlId, SearchModel searchModel);

    /**
     * 查询指定SQL语句的所有记录
     *
     * @param sqlId
     *         SQL语句ID
     * @param clazz
     *         返回值类型
     * @param params
     *         条件参数
     *
     * @return 查询到的结果集合
     */
    public <T> List<T> findAll(String sqlId, Class<T> clazz, Map<String, Object> params);

    /**
     * 查询指定SQL语句的所有记录
     *
     * @param sqlId
     *         SQL语句ID
     * @param clazz
     *         返回值类型
     * @param param
     *         条件参数
     *
     * @return 查询到的结果集合
     */
    public <T> List<T> findAll(String sqlId, Class<T> clazz, int param);

    /**
     * 分页查询指定SQL语句的数据
     *
     * @param countSqlId
     *         总数查询SQL语句ID
     * @param sqlId
     *         SQL语句ID
     * @param searchModel
     *         查询模型
     * @param clazz
     *         查询结果类型
     *
     * @return 分页查询结果PagingResult
     */
    public <T extends BaseEntity> PagingResult<T> findForPage(String countSqlId, String sqlId, SearchModel searchModel, Class<T> clazz);

    /**
     * 分页查询指定SQL语句的数据（ID）
     *
     * @param countSqlId
     *         总数查询SQL语句ID
     * @param sqlId
     *         SQL语句ID
     * @param searchModel
     *         查询模型
     *
     * @return 分页查询结果List<Integer>
     */
    public PagingResult<Integer> findForPage(String countSqlId, String sqlId, SearchModel searchModel);

    /**
     * 查询指定SQL语句的一条记录
     *
     * @param sqlId
     *         SQL语句ID
     *
     * @return 查询到的实体
     */
    public <T extends BaseEntity> T findOne(String sqlId);

    /**
     * 根据条件查询指定SQL语句的一条记录
     *
     * @param sqlId
     *         SQL语句ID
     * @param clazz
     *         返回值类型
     * @param params
     *         条件参数
     *
     * @return 查询到的结果
     */
    public <T> T findOne(String sqlId, Class<T> clazz, Map<String, Object> params);

    /**
     * 根据条件查询指定SQL语句的一条记录，主要用于关联查询的情况
     *
     * @param sqlId
     *         SQL语句ID
     * @param clazz
     *         返回值类型
     * @param param
     *         条件参数
     *
     * @return 查询到的结果
     */
    public <T> T findOne(String sqlId, Class<T> clazz, int param);

    /**
     * 查询指定SQL语句的一条记录
     *
     * @param sqlId
     *         SQL语句ID
     * @param searchModel
     *         SQL语句中占位符对应的值
     *
     * @return 查询到的实体
     */
    public <T extends BaseEntity> T findOne(String sqlId, SearchModel searchModel);

    /**
     * 查询指定SQL语句所包含的记录数
     *
     * @param sqlId
     *         SQL语句ID
     *
     * @return 记录数
     */
    public long count(String sqlId);

    /**
     * 查询指定SQL语句所包含的记录数
     *
     * @param sqlId
     *         SQL语句ID
     * @param params
     *         参数
     *
     * @return 符合条件的记录数
     */
    public long count(String sqlId, Map<String, Object> params);

    /**
     * 查询指定SQL语句所包含的记录数
     *
     * @param sqlId
     *         SQL语句ID
     * @param searchModel
     *         查询模型
     *
     * @return 符合条件的记录数
     */
    public long count(String sqlId, SearchModel searchModel);

    /**
     * 插入指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     *
     * @return 插入对象的主键
     */
    public Object save(String sqlId);

    /**
     * 插入指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     * @param entity
     *         要插入的实体
     *
     * @return 插入对象的主键
     */
    public Object save(String sqlId, BaseEntity entity);

    /**
     * 更新指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     *
     * @return 成功更新的记录数
     */
    public int update(String sqlId);

    /**
     * 更新指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     * @param entity
     *         要更新的对象
     *
     * @return 成功更新的记录数
     */
    public int update(String sqlId, BaseEntity entity);

    /**
     * 更新指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     * @param params
     *         参数
     *
     * @return 成功更新的记录数
     */
    public int update(String sqlId, Map<String, Object> params);

    /**
     * 删除指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     *
     * @return 成功删除的记录数
     */
    public int delete(String sqlId);

    /**
     * 删除指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     * @param params
     *         查询参数
     *
     * @return 成功删除记录数
     */
    public int delete(String sqlId, Map<String, Object> params);

    /**
     * 批量删除指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     * @param params
     *         SQL语句中占位符对应的值
     *
     * @return 成功更新的记录数
     */
    public int[] batchDelete(final String sqlId, final List<BaseEntity> params);

    /**
     * 批量更新指定SQL的数据
     *
     * @param sqlId
     *         SQL语句ID
     * @param params
     *         SQL语句中占位符对应的值
     *
     * @return 成功更新的记录数
     */
    public int[] batchUpdate(final String sqlId, final List<? extends BaseEntity> params);

    /**
     * 查询指定SQL的实例（查询主库）
     *
     * @param sqlId
     *         SQL ID
     * @param <T>
     *         返回值实体类型
     *
     * @return 实体对象
     */
    public <T extends BaseEntity> T findOneByM(String sqlId);

    /**
     * 查询指定SQL的实例（查询主库）
     *
     * @param sqlId
     *         SQL ID
     * @param clazz
     *         返回值实体类型
     * @param params
     *         SQL参数集合
     * @param <T>
     *         返回值实体类型
     *
     * @return 实体
     */
    public <T> T findOneByM(String sqlId, Class<T> clazz, Map<String, Object> params);

    /**
     * 查询指定SQL的实例（查询主库）
     *
     * @param sqlId
     *         SQL ID
     * @param searchModel
     *         条件模型
     * @param <T>
     *         返回值实体类型
     *
     * @return 实体
     */
    public <T extends BaseEntity> T findOneByM(String sqlId, SearchModel searchModel);

    /**
     * 查询指定SQL的实例（查询主库）
     *
     * @param sqlId
     *         SQL ID
     * @param clazz
     *         返回值实体类型
     * @param param
     *         SQL参数集合
     * @param <T>
     *         返回值实体类型
     *
     * @return 查询结果
     */
    public <T> T findOneByM(String sqlId, Class<T> clazz, int param);

    /**
     * 查询指定SQL的实例（查询主库）
     *
     * @param sqlId
     *         SQL ID
     *
     * @return 查询结果
     */
    public long countByM(String sqlId);

    /**
     * 查询指定SQL的实例（查询主库）
     *
     * @param sqlId
     *         SQL ID
     * @param params
     *         参数集合
     *
     * @return 查询结果
     */
    public long countByM(String sqlId, Map<String, Object> params);

    /**
     * 查询指定SQL的实例（查询主库）
     *
     * @param sqlId
     *         SQL ID
     * @param clazz
     *         返回值实体类型
     * @param params
     *         参数集合
     * @param <T>
     *         返回值实体类型
     *
     * @return 查询结果
     */
    public <T> List<T> findAllByM(String sqlId, Class<T> clazz, Map<String, Object> params);

    /**
     * 查询指定SQL的实例（查询主库）
     *
     * @param sqlId
     *         SQL ID
     * @param <T>
     *         返回值实体类型
     *
     * @return 查询结果
     */
    public <T extends BaseEntity> List<T> findAllByM(String sqlId);
}
