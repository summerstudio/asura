/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file EscapeTool 
 * @package com.asura.framework.velocity.directive 
 *
 * @date 2015/4/1 15:16 
 */
package com.asura.framework.velocity.directive;

import com.asura.framework.base.util.Check;
import org.apache.commons.lang3.StringEscapeUtils;
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
 * HTMLescape工具 防止脚本XSS攻击
 * 使用实例如下：
 * #escape(${v.htmlcontent})
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
public class EscapeTool extends Directive {

    public static final Logger ERRORLOG = LoggerFactory.getLogger(Directive.class);

    @Override
    public String getName() {
        return "escape";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        try {
            String escapeHtml = "";
            if (!Check.NuNObj(node.jjtGetChild(0)) && !Check.NuNObj(node.jjtGetChild(0).value(context))) {
                escapeHtml = String.valueOf(node.jjtGetChild(0).value(context));
            }
            writer.write(escape(escapeHtml));
        } catch (Exception e) {
            ERRORLOG.error("Velocity EscapeTool Error:" + e);
            writer.write("");
        }
        return true;
    }

    private String escape(String escapeHtml) {
        if (Check.NuNStr(escapeHtml)) {
            return escapeHtml;
        }
        escapeHtml = StringEscapeUtils.escapeHtml4(escapeHtml);
        //处理空格
        escapeHtml.replace(" ", "&nbsp;");
        //处理换行
        escapeHtml.replace("\r\n", "<br/>").replace("\n", "<br/>");
        return escapeHtml;
    }
}
