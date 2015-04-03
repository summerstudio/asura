/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SearchModel 
 * @package com.asura.framework.base.paging 
 *
 * @date 2015/3/13 15:30 
 */
package com.asura.framework.base.paging;

import java.io.Serializable;
import java.util.List;

/**
 * <p> SQL查询模型 </P>
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
public class SearchModel implements Serializable {

    private static final long serialVersionUID = 1526752111986235054L;

    /**
     * 当前页码
     */
    private int currentPage;

    /**
     * 每页记录数
     */
    private int pageSize = 20;

    /**
     * 查询条件组
     */
    private List<SearchCondition> searchConditionList;

    /**
     * 排序
     */
    private List<Sort> sortList;

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *         the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;

        if (currentPage < 1) {
            this.currentPage = 1;
        }
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *         the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;

        if (pageSize < 1) {
            this.pageSize = 20;
        }
    }

    /**
     * @return the searchConditionList
     */
    public List<SearchCondition> getSearchConditionList() {
        return searchConditionList;
    }

    /**
     * @param searchConditionList
     *         the searchConditionList to set
     */
    public void setSearchConditionList(List<SearchCondition> searchConditionList) {
        this.searchConditionList = searchConditionList;
    }

    /**
     * @return the sortList
     */
    public List<Sort> getSortList() {
        return sortList;
    }

    /**
     * @param sortList
     *         the sortList to set
     */
    public void setSortList(List<Sort> sortList) {
        this.sortList = sortList;
    }

    /**
     * 得到当前页第一行数据的索引
     *
     * @return 当前页第一行数据的索引
     */
    public int getFirstRowIndex() {
        return ((currentPage - 1) * pageSize);
    }
}
