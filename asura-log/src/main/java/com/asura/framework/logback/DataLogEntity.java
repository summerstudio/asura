/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file DataLogEntity 
 * @package com.asura.framework.logback 
 *
 * @date 2015/3/30 15:29 
 */
package com.asura.framework.logback;

import com.asura.framework.base.entity.BaseEntity;

/**
 * <p> 系统日志数据日志实体 </P>
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
public class DataLogEntity extends BaseEntity {

    private static final long serialVersionUID = -6651936782310151087L;

    public DataLogEntity() {
    }

    public DataLogEntity(String className, String methodName, Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.params = params;
    }

    //类名
    private String className;
    //方法名
    private String methodName;
    //参数
    private Object[] params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
