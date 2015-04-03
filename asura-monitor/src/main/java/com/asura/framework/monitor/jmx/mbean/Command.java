/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file Command 
 * @package com.asura.framework.monitor.jmx.mbean 
 *
 * @date 2015/3/31 11:06 
 */
package com.asura.framework.monitor.jmx.mbean;

import java.io.IOException;

/**
 * <p> 命令信息 </P>
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
public class Command implements CommandMBean {

    @Override
    public boolean startApplication(String path) {
        String os = System.getProperties().getProperty("os.name");
        try {
            if (os.toLowerCase().startsWith("win")) {
                Runtime.getRuntime().exec("cmd /k start " + path + "/bin/start.bat");
            } else {
                Runtime.getRuntime().exec("sh " + path + "/bin/start.sh");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean stopApplication(String path) {
        String os = System.getProperties().getProperty("os.name");
        try {
            if (os.toLowerCase().startsWith("win")) {
                Runtime.getRuntime().exec("cmd /k stop " + path + "/bin/start.bat");
            } else {
                Runtime.getRuntime().exec("sh " + path + "/bin/stop.sh");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
