/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file JsonEntityTransform 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/12 16:48 
 */
package com.asura.framework.base.util;

import com.asura.framework.base.entity.BaseEntity;
import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.exception.BusinessException;
import com.asura.framework.base.paging.SearchModel;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> Json与Entity互相转化 </P>
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
public class JsonEntityTransform {

    /**
     * 将json转换成Entity
     *
     * @param json
     *         要转换的json数据
     * @param clazz
     *         目标实体
     *
     * @return Entity    转换后的实体对象
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static <T extends BaseEntity> T json2Entity(String json, Class<T> clazz) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        T entity = null;
        try {
            entity = mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成Entity时出现异常。", e);
        }
        return entity;
    }

    /**
     * 将json转换成Object
     *
     * @param json
     *         要转换的json数据
     * @param clazz
     *         目标实体
     *
     * @return Entity    转换后的实体对象
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static <T> T json2Object(String json, Class<T> clazz) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成Object时出现异常。", e);
        }
        return t;
    }


    /**
     * 将json转换成DataTransferObject
     *
     * @param json
     *         要转换的json数据
     *
     * @return DataTransferObject    转换后的实体对象
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static DataTransferObject json2DataTransferObject(String json) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        DataTransferObject dto = null;
        try {
            dto = mapper.readValue(json, DataTransferObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成DataTransferObject时出现异常。", e);
        }
        return dto;
    }

    /**
     * 将json转换成SearchModel
     *
     * @param json
     *         要转换的json数据
     *
     * @return SearchModel 转换后的实体对象
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static SearchModel json2SearchModel(String json) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        SearchModel searchModel = null;
        try {
            searchModel = mapper.readValue(json, SearchModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成SearchModel时出现异常。", e);
        }
        return searchModel;
    }

    /**
     * 将Json转换成List类型集合
     *
     * @param json
     *         要转换的json数据
     * @param clazz
     *         转换后的实体类型
     *
     * @return 转换后的实体集合
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static <T extends BaseEntity> List<T> json2EntityList(String json, Class<T> clazz) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return (List<T>) mapper.readValue(json, javaType);
        } catch (JsonParseException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成List<T>异常。", e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成List<T>异常。", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成List<T>异常。", e);
        }
    }

    /**
     * json转换list通用
     *
     * @param json
     *         要转换的json数据
     * @param clazz
     *         转换后的实体类型
     *
     * @return 转换后的实体集合
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static <T> List<T> json2ObjectList(String json, Class<T> clazz) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return mapper.readValue(json, javaType);
        } catch (JsonParseException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成List<T>异常。", e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成List<T>异常。", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成List<T>异常。", e);
        }
    }

    /**
     * 将json转换成String
     *
     * @param json
     *         json数据
     *
     * @return String    转换后的字符
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static String json2String(String json) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.readValue(json, String.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成String时出现异常。", e);
        }
        return result;
    }

    /**
     * 将json数据转换成Map
     *
     * @param json
     *         要转换的json数据
     *
     * @return Map 转换后的结果
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static Map<?, ?> json2Map(String json) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> result = null;
        try {
            result = mapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Json转换成Map时出现异常。", e);
        }
        return result;
    }

    /**
     * 将包含Entity的数据传输对象转换成Json
     *
     * @param dto
     *         DataTransferObject对象
     *
     * @return json    转换后的json数据
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static String dataTransferObject2Json(DataTransferObject dto) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, dto);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("DataTransferObject转换成Json时出现异常。", e);
        }
        return writer.toString();
    }

    /**
     * 将自定义对象转json
     *
     * @param object
     *         要转换的对象
     *
     * @return json    转换后的json数据
     *
     * @throws BusinessException
     *         类型转换异常
     */
    public static String Object2Json(Object object) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, object);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Object转换成Json时出现异常。", e);
        }
        return writer.toString();
    }

}
