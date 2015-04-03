/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SearchCondition 
 * @package com.asura.framework.base.paging 
 *
 * @date 2015/3/13 15:32 
 */
package com.asura.framework.base.paging;

import java.io.Serializable;

/**
 * <p> SQL查询条件 </P>
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
public class SearchCondition implements Serializable {

    private static final long serialVersionUID = -8263813577950149683L;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数比较类型
     */
    private int operateType;

    /**
     * 参数值
     */
    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
