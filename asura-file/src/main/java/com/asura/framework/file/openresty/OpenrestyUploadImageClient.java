/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file OpenrestyUploadImageClient 
 * @package com.asura.framework.file.openresty 
 *
 * @date 2015/4/14 17:03 
 */
package com.asura.framework.file.openresty;

import com.asura.framework.base.entity.DataTransferObject;
import com.asura.framework.base.exception.BusinessException;
import com.asura.framework.base.util.JsonEntityTransform;
import com.asura.framework.conf.subscribe.ConfigSubscriber;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.InputStream;
import java.util.Map;

/**
 * <p> 图片上传 </P>
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
public class OpenrestyUploadImageClient {

    /**
     * 上传图片
     *
     * @param from
     *         系统来源
     * @param format
     *         图片格式
     * @param in
     *         输入流
     *
     * @return
     *
     * @throws BusinessException
     */
    public static String uploadImage(String from, String format, InputStream in) throws BusinessException {
        String value = "";
        StringBuffer url_part = getImageUrl();
        String url = url_part.append("?from=").append(from).append("&format=").append(format).toString();
        DataTransferObject dto = request(url, in);
        if (dto != null) {
            if (dto.getCode() == 0) {
                value = String.class.cast(dto.getData().get("data"));
            } else {
                throw new BusinessException(dto.getMsg());
            }
        }
        return value;
    }


    /**
     * 获取访问图片服务器的url
     *
     * @return
     */
    private static StringBuffer getImageUrl() {
        String type = EnumSysConfig.asura_openresty_image_url.getType();
        String code = EnumSysConfig.asura_openresty_image_url.getCode();
        String value = ConfigSubscriber.getInstance().getConfigValue(type, code);
        return new StringBuffer(value);
    }


    /**
     * 请求图片服务器
     *
     * @param url
     *         对应的图片服务器地址
     * @param in
     *         输入流
     *
     * @return
     *
     * @throws BusinessException
     */
    @SuppressWarnings({"deprecation", "unchecked"})
    private static DataTransferObject request(String url, InputStream in) throws BusinessException {
        DataTransferObject dto = new DataTransferObject();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 2000);
        method.setRequestBody(in);
        int statusCode = 0;
        try {
            client.executeMethod(method);
            statusCode = method.getStatusCode();
            String returnJson = method.getResponseBodyAsString();
            // 成功{"status":"1","data":"http://192.168.0.17:8081/leader/2015/04/02/14279641358147856.jpg"}
            // 失败{"status":"0","data":"失败原因"}
            System.out.println(returnJson);
            Map<String, Object> map = (Map<String, Object>) JsonEntityTransform.json2Map(returnJson);
            String status = String.class.cast(map.get("status"));
            String data = String.class.cast(map.get("data"));
            if ("1".equals(status)) {
                dto.putValue("data", data);
            } else {
                dto.setErrCode(1);
                dto.setMsg(data);
                throw new BusinessException(data);
            }
        } catch (Throwable t) {
            dto.setErrCode(1);
            dto.setMsg("访问图片服务器地址异常");
            throw new BusinessException("访问图片服务器地址异常:" + t.getMessage());
        } finally {
            if (statusCode == HttpStatus.SC_OK) {
                dto.setMsg("请求成功，返回 code=" + statusCode);
            } else {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("请求失败，返回 code=" + statusCode);
            }
            //释放链接
            method.releaseConnection();
        }
        return dto;
    }

    public static void main(String[] args) {

        InputStream in = OpenrestyUploadImageClient.class.getResourceAsStream("/com/asura/framework/file/openresty/111.jpg");
        System.out.println(uploadImage("leader", "jpg", in));
    }
}
