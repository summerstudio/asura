/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file PriceFormatTool 
 * @package com.asura.framework.velocity.directive 
 *
 * @date 2015/4/1 15:10 
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

/**
 * <p>
 * 商品售价格式话指令，去掉末尾的小数点后的0，如:
 * 6900 返回 69
 * 6780 返回 67.8
 * 6789 返回67.89
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
public class PriceFormatTool extends Directive {

    public static final Logger ERRORLOG = LoggerFactory.getLogger(Directive.class);

    @Override
    public String getName() {
        return "priceFormat";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        try {
            double price = 0.0;
            if (!Check.NuNObj(node.jjtGetChild(0)) && !Check.NuNObj(node.jjtGetChild(0).value(context))) {
                price = Double.valueOf(String.valueOf(node.jjtGetChild(0).value(context)));
            }
            writer.write(doubleTrans(price));
        } catch (Exception e) {
            ERRORLOG.error("Velocity PriceFormatTool Error:" + e);
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
