/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file PagingResult 
 * @package com.asura.framework.base.paging 
 *
 * @date 2015/3/18 9:39 
 */
package com.asura.framework.base.paging;

import com.asura.framework.base.entity.DataTransferObject;

import java.io.Serializable;
import java.util.List;

/**
 * <p> 分页查询结果 </P>
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
public class PagingResult<T> implements Serializable {

    private static final long serialVersionUID = -7520932527124939004L;

    /**
     * 分页查询结果构造器
     *
     * @param total
     *         总记录数
     * @param rows
     *         每页记录
     */
    public PagingResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    /**
     * 总记录数
     */
    private long total;

    /**
     * 每页数据记录
     */
    private List<T> rows;

    /**
     * @return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total
     *         the total to set
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * @return the rows
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * @param rows
     *         the rows to set
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * 将PagingResult转换为DataTransferObject
     *
     * @param key
     *         放入DataTransferObject时需要的key值
     *
     * @return 新的DataTransferObject对象
     */
    public DataTransferObject toDataTransferObject(String key) {
        DataTransferObject dto = new DataTransferObject();
        dto.putValue(key, rows);
        return dto;
    }
}
