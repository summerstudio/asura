/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file RateTool 
 * @package com.asura.framework.velocity.directive 
 *
 * @date 2015/4/1 15:07 
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
import java.math.BigDecimal;

/**
 * <p>
 * 百分比率 将小数变换成百分比
 * 用例：#rate($floatNum)
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
public class RateTool extends Directive {

    public static final Logger ERRORLOG = LoggerFactory.getLogger(Directive.class);

    @Override
    public String getName() {
        return "rate";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        try {
            String price = "0.0";
            if (node.jjtGetChild(0) != null) {
                price = String.valueOf(node.jjtGetChild(0).value(context));
            }
            if (Check.NuNStr(price)) {
                price = "0.0";
            }
            BigDecimal bd1 = new BigDecimal(price);
            BigDecimal bd2 = new BigDecimal("100");
            String res = bd1.multiply(bd2).toString();
            writer.write(doubleTrans(Math.round(Double.valueOf(res))));
        } catch (Exception e) {
            ERRORLOG.error("Velocity RateTool Error:" + e);
            writer.write("0");
        }
        return true;
    }

    private String doubleTrans(double d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

}
