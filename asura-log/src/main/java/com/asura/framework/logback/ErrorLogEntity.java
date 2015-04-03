/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file ErrorLogEntity 
 * @package com.asura.framework.logback 
 *
 * @date 2015/3/30 15:32 
 */
package com.asura.framework.logback;

/**
 * <p> 系统日志错误日志实体 </P>
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
public class ErrorLogEntity extends DataLogEntity {

    private static final long serialVersionUID = 6143332279421541273L;

    //抛出异常参数
    private String throwMessage;

    public ErrorLogEntity(DataLogEntity dle) {
        super(dle.getClassName(), dle.getMethodName(), dle.getParams());
    }

    public String getThrowMessage() {
        return throwMessage;
    }

    public void setThrowMessage(String throwMessage) {
        this.throwMessage = throwMessage;
    }
}
