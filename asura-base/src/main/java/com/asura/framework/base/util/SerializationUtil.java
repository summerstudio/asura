/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file SerializationUtil 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/18 11:30 
 */
package com.asura.framework.base.util;

import java.io.*;

/**
 * <p> 序列化及反序列化工具类 </P>
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
public class SerializationUtil {

    /**
     * 私有构造器
     */
    private SerializationUtil() {
    }

    /**
     * 深度克隆对象
     *
     * @param object
     *         可序列化的被克隆对象
     *
     * @return 克隆后的对象
     */
    public static Object clone(Serializable object) {
        return deserialize(serialize(object));
    }

    /**
     * 将对象序列化为字节数组
     *
     * @param obj
     *         要序列化的对象
     *
     * @return 序列化后的字节数组
     */
    public static byte[] serialize(Serializable obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 将字节数组反序列化为对象
     *
     * @param objectData
     *         要反序列化的字节数组
     *
     * @return 反序列化后的对象
     */

    public static Object deserialize(byte[] objectData) {
        if (objectData == null) {
            throw new IllegalArgumentException("The byte[] must not be null");
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(bais);
            return in.readObject();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
