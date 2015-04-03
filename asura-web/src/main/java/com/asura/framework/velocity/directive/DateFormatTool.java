/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file DateFormatTool 
 * @package com.asura.framework.velocity.directive 
 *
 * @date 2015/4/1 15:27 
 */
package com.asura.framework.velocity.directive;

import com.asura.framework.base.util.Check;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * velocity directive sample
 * 1) extends Directive Class and implements render method
 * 2) declaration this class in classpath:velocity.properties
 * note：this directive only handler second level timestamp
 * if handler a millSecond timestamp please see <link>MillSecondDateFormatTool</link>
 * usage：
 * #dateFormat(${secondDate})
 * </P>
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
public class DateFormatTool extends Directive {

    public static final String DEFAULT_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final Logger ERRORLOG = LoggerFactory.getLogger(Directive.class);

    @Override
    public String getName() {
        return "dateFormat";
    }

    @Override
    public int getType() {

        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        try {
            Integer timeStampSecond = null;
            String formatPattern = DEFAULT_FORMAT_PATTERN;
            //params
            if (!Check.NuNObj(node.jjtGetChild(0)) && !Check.NuNObj(node.jjtGetChild(0).value(context))) {
                timeStampSecond = Integer.valueOf(String.valueOf(node.jjtGetChild(0).value(context)));
            }
            if (!Check.NuNObj(node.jjtGetChild(1)) && !Check.NuNObj(node.jjtGetChild(1).value(context))) {
                formatPattern = String.valueOf(node.jjtGetChild(1).value(context));
            }
            if (timeStampSecond == 0L || timeStampSecond == null) {
                writer.write("--");
            } else {
                writer.write(format(timeStampSecond, formatPattern));
            }
        } catch (Exception e) {
            ERRORLOG.error("Velocity DateFormatTool Error:" + e);
            writer.write("--");
        }
        return true;

    }

    private String format(Integer timeStampSecond, String formatPattern) {
        Date date = new Date(timeStampSecond * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
        return sdf.format(date);
    }
}
