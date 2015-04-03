/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file MillSecondDateFormatTool 
 * @package com.asura.framework.velocity.directive 
 *
 * @date 2015/4/1 15:14 
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
 * <p> 毫秒转化工具 </P>
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
public class MillSecondDateFormatTool extends Directive {

    public static final Logger ERRORLOG = LoggerFactory.getLogger(Directive.class);

    public static final String DEFAULT_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public String getName() {
        return "millDateFormat";
    }

    @Override
    public int getType() {

        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        try {
            Long timeStampSecond = null;
            String formatPattern = DEFAULT_FORMAT_PATTERN;
            if (!Check.NuNObj(node.jjtGetChild(0)) && !Check.NuNObj(node.jjtGetChild(0).value(context))) {
                timeStampSecond = Long.valueOf(String.valueOf(node.jjtGetChild(0).value(context)));
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
            ERRORLOG.error("Velocity MillSecondDateFormatTool Error:" + e);
            writer.write("--");
        }
        return true;

    }

    private String format(Long timeStampSecond, String formatPattern) {
        Date date = new Date(timeStampSecond);
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
        return sdf.format(date);
    }
}
