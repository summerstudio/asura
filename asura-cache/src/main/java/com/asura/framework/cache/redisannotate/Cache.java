/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file Cache 
 * @package com.asura.framework.cache.redisannotate 
 *
 * @date 2015/3/23 10:02 
 */
package com.asura.framework.cache.redisannotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 缓存注解 </P>
 *
 * <p>
 * 四个元注解分别是：@Target,@Retention,@Documented,@Inherited ，再次强调下元注解是java API提供，是专门用来定义注解的注解，其作用分别如下。
 *
 * @Target : 表示该注解用于什么地方，可能的值在枚举类 ElemenetType 中，包括：
 * ElemenetType.CONSTRUCTOR        构造器声明
 * ElemenetType.FIELD          　　　　 域声明（包括 enum 实例）
 * ElemenetType.LOCAL_VARIABLE     局部变量声明
 * ElemenetType.METHOD           　　 方法声明
 * ElemenetType.PACKAGE          　　 包声明
 * ElemenetType.PARAMETER             参数声明
 * ElemenetType.TYPE          　　　　  类，接口（包括注解类型）或enum声明
 *
 * @Retention : 表示在什么级别保存该注解信息。可选的参数值在枚举类型 RetentionPolicy 中，包括：
 * RetentionPolicy.SOURCE 　　　    注解将被编译器丢弃
 * RetentionPolicy.CLASS 　　　　　 注解在class文件中可用，但会被VM丢弃
 * RetentionPolicy.RUNTIME 　　　　VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。
 *
 * @Documented : 将此注解包含在 javadoc 中 ，它代表着此注解会被javadoc工具提取成文档。在doc文档中的内容会因为此注解的信息内容不同而不同。相当与@see,@param 等。
 *
 * @Inherited : 在您定义注解后并使用于程序代码上时，预设上父类别中的注解并不会被继承至子类别中，您可以在定义注解时加上java.lang.annotation.Inherited 限定的Annotation，这让您定义的Annotation型别被继承下来。注意注解继承只针对class 级别注解有效
 * </p>
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
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

    DataStructure dataStructure() default DataStructure.hash;

    /**
     * 缓存key（相当于dbname）
     *
     * @return
     */
    String key();

    /**
     * 字段key（相当于map中的key）
     *
     * @return
     */
    String fieldKey() default "";

    /**
     * 缓存过期时间
     *
     * @return
     */
    int expireTime() default 3600;

    /**
     * true缓存的使用不受外部空值
     *
     * @return
     */
    boolean selfControl() default false;

    /**
     * true 更新缓存中的数据
     *
     * @return
     */
    boolean isUpdate() default false;
}
