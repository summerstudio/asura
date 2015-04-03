/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file Sort 
 * @package com.asura.framework.base.paging 
 *
 * @date 2015/3/13 17:01 
 */
package com.asura.framework.base.paging;

import java.io.Serializable;

/**
 * <p> SQL查询排序 </P>
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
public class Sort implements Serializable {

    private static final long serialVersionUID = 6874749132013920842L;

    public static final int SORT_ASC = 0;

    public static final int SORT_DESC = 1;

    private Sort(int sortType, String field) {
        this.sortType = sortType;
        this.field = field;
    }

    /**
     * 0：降序；1升序
     */
    private int sortType;

    /**
     * 排序字段名称
     */
    private String field;

    public static Sort createSort(int sortType, String field) {
        return new Sort(sortType, field);
    }

    /**
     * @return the sortType
     */
    public int getSortType() {
        return sortType;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }
}
