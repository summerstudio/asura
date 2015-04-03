/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file CallbackAdapter 
 * @package com.asura.framework.cache.redisannotate.callback 
 *
 * @date 2015/3/23 16:04 
 */
package com.asura.framework.cache.redisannotate.callback;

import java.util.Map;

/**
 * <p> 回调适配器 </P>
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
public abstract class CallbackAdapter<T> implements Callback<T> {

    @Override
    public T callback(T t) throws Exception {
        return null;
    }

    @Override
    public T callback(Map<String, String> map) throws Exception {
        return null;
    }

}
