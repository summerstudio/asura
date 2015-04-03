/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SystemLogger 
 * @package com.asura.framework.logback 
 *
 * @date 2015/3/30 16:07 
 */
package com.asura.framework.logback;

import com.alibaba.dubbo.rpc.RpcContext;
import com.asura.framework.base.context.ApplicationContext;
import com.asura.framework.base.util.IPUtil;
import com.asura.framework.conf.subscribe.ConfigSubscriber;
import org.apache.log4j.MDC;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p> 系统日志AOP </P>
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
@Component
@Aspect
public class SystemLogger {

    private static final Logger logger = LoggerFactory.getLogger(SystemLogger.class);

    /**
     * 客户地址
     */
    private final static String HOST = "address";

    /**
     * 调用的接口
     */
    private final static String INTERFACE = "service";

    /**
     * 调用的方法名称
     */
    private final static String METHOD = "method";

    //    private MailSendService mailSendService;

    @Around("execution(* com.asura..*.proxy.*.* (..))")
    public Object doBasicProfiling(final ProceedingJoinPoint joinPoint) throws Throwable {
        if (RpcContext.getContext().getRemoteAddressString() != null && RpcContext.getContext().getMethodName() != null && RpcContext.getContext().getUrl() != null) {
            MDC.put(HOST, RpcContext.getContext().getRemoteAddressString());
            MDC.put(INTERFACE, RpcContext.getContext().getUrl().getServiceInterface());
            MDC.put(METHOD, RpcContext.getContext().getMethodName());
        }

        final DataLogEntity de = new DataLogEntity();
        de.setClassName(joinPoint.getSignature().getDeclaringTypeName());
        de.setMethodName(joinPoint.getSignature().getName());
        de.setParams(joinPoint.getArgs());
        //记录service层的数据日志
        if (logger.isInfoEnabled()) {
            logger.info(de.toJsonStr());
        }
        try {
            final Object retVal = joinPoint.proceed();
            //记录service层，方法执行返回值日志
            final ReturnLogEntity rle = new ReturnLogEntity(de);
            rle.setReturnVal(retVal);
            //有些方法不记录日志
            //if (logger.isInfoEnabled()) {
            //				logger.info(rle.toJsonStr());
            //}
            return retVal;
        } catch (final Exception e) {
            final ErrorLogEntity ele = new ErrorLogEntity(de);
            ele.setThrowMessage(e.toString());
            //记录service层抛出来的异常
            logger.error(ele.toJsonStr());
            throw e;
        } finally {
            MDC.remove(HOST);
            MDC.remove(INTERFACE);
            MDC.remove(METHOD);
        }
    }

    @Around("execution(* com.asura..*.service.impl.*.* (..))")
    public Object doErrorLog(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            final Object retVal = joinPoint.proceed();
            return retVal;
        } catch (final Exception e) {
            final DataLogEntity de = new DataLogEntity();
            de.setClassName(joinPoint.getSignature().getDeclaringTypeName());
            de.setMethodName(joinPoint.getSignature().getName());
            de.setParams(joinPoint.getArgs());
            final ErrorLogEntity ele = new ErrorLogEntity(de);
            ele.setThrowMessage(e.toString());
            //            MailInfoVO mail = new MailInfoVO();
            String type = EnumSysConfig.asura_bugmail.getType();
            String code = EnumSysConfig.asura_bugmail.getCode();
            String value = null;
            String valuegetinfo = "获取bugmail正常";
            try {
                value = ConfigSubscriber.getInstance().getConfigValue(type, code);
            } catch (Exception e2) {
                valuegetinfo = "获取bugmail异常";
                value = EnumSysConfig.asura_bugmail.getDefaultValue();
            }
            if (value == null) {
                valuegetinfo = "获取bugmail为空值";
                value = EnumSysConfig.asura_bugmail.getDefaultValue();
            }
            //            mail.setMailAddrs(value);
            //            mail.setContent(valuegetinfo + "--" + ele.toJsonStr());
            //            mail.setMtype(EnumMailType.bug.getCode());
            //            mail.setSource("logback");
            //            mail.setSubject(IPUtil.getLocalAddress().getHostAddress() + "   " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            //            try {
            //                mailSendService = (MailSendService) ApplicationContext.getContext().getBean("message.mailSendService");
            //            } catch (Exception e1) {
            //                mailSendService = (MailSendService) ApplicationContext.getContext()
            //                        .getBean("mail.mailSendServiceProxy");
            //            }
            //            mailSendService.sendMailInfo(mail);
            //记录service层抛出来的异常
            logger.error(ele.toJsonStr());
            throw e;
        }
    }
}
