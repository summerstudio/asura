/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file HttpUrlConnectionUtil
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/19 17:36 
 */
package com.asura.framework.base.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> HttpUrlConnection工具类 </P>
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
public class HttpUrlConnectionUtil {

    /**
     * 默认客户端工具
     */
    public static final String Mammoth_Ua = "iPhone";

    /**
     * 请求URL
     */
    private String m_url = null;

    /**
     * GET请求参数集合
     */
    private Map<String, String> m_getpar = null;

    /**
     * POST请求参数集合
     */
    private Map<String, String> m_postpar = null;

    /**
     *
     */
    private byte[] m_postbody = null;

    /**
     * 请求header集合
     */
    private Map<String, String> m_requestheader = null;

    /**
     * http请求认证字符串
     */
    private String m_authorization = null;

    /**
     * 返回结果编码
     */
    private int m_result = 500;

    /**
     * 返回header集合
     */
    private Map<String, List<String>> m_responseheader = null;

    /**
     * 目标URL反馈的二进制文件体
     */
    private byte[] m_pagebody = null;

    /**
     * 请求返回信息
     */
    private String m_responsemessage = null;

    /**
     * 链接超时时间
     */
    private int m_conntimeout = 1000;

    /**
     * 读取超时时间
     */
    private int m_readtimeout = 5000;

    /**
     * 返回内容大小
     */
    private int m_responsebodysize;

    /**
     * 创建对象
     */
    public HttpUrlConnectionUtil() {
        super();
    }

    /**
     * 创建针对某个url访问的对象
     *
     * @param url
     *         请求URL
     */
    public HttpUrlConnectionUtil(final String url) {
        super();
        setUrl(url);
    }

    /**
     * 增加GET协议的参数
     *
     * @param key
     *         参数名称
     * @param value
     *         参数值
     */
    public void addGetPar(final String key, final String value) {
        if (strIsRight(key) && strIsRight(value)) {
            if (m_getpar == null)
                m_getpar = new HashMap<String, String>();
            m_getpar.put(key, value);
        }
    }

    /**
     * 增加POST的参数
     *
     * @param key
     *         参数名称
     * @param value
     *         参数值
     */
    public void addPostPar(final String key, final String value) {
        if (strIsRight(key) && strIsRight(value)) {
            if (m_postpar == null)
                m_postpar = new HashMap<String, String>();
            m_postpar.put(key, value);
        }
    }

    /**
     * 增加请求目标URL的HEAD参数
     *
     * @param key
     *         参数名称
     * @param value
     *         参数值
     */
    public void addRequestHeader(final String key, final String value) {
        if (strIsRight(key) && strIsRight(value)) {
            if (m_requestheader == null)
                m_requestheader = new HashMap<String, String>();
            m_requestheader.put(key, value);
        }
    }

    /**
     * 获取响应头信息
     *
     * @param urlconn
     *         HttpURLConnection
     */
    private void collectResponseHeader(final HttpURLConnection urlconn) {
        m_responseheader = urlconn.getHeaderFields();
    }

    /**
     * 提交链接请求
     *
     * @return 目标URL返回的回应值（一般应为200）
     *
     * @throws Exception
     */
    public int connect() throws Exception {
        return connect(true);
    }

    /**
     * 请求URL并返回响应状态码
     * @param readbody 是否读取响应内容
     * @return
     * @throws Exception
     */
    public int connect(final boolean readbody) throws Exception {
        m_result = 510;
        if (m_url != null) {
            HttpURLConnection urlconn = null;
            OutputStream out = null;
            InputStream in = null;
            ByteArrayOutputStream brbuf = null;
            final String urlname = fixUrl(m_url, m_getpar);
            try {
                final URL url = new URL(urlname);
                if (url != null) {
                    final String poststr = getPar(m_postpar);
                    urlconn = (HttpURLConnection) url.openConnection();
                    if (urlconn != null) {
                        urlconn.setInstanceFollowRedirects(true);
                        urlconn.setConnectTimeout(m_conntimeout);
                        urlconn.setReadTimeout(m_readtimeout);
                        urlconn.setUseCaches(false);
                        if (m_authorization != null)
                            urlconn.addRequestProperty("Authorization", m_authorization);
                        doWithHeader(urlconn, m_requestheader);
                        urlconn.setDoInput(true);
                        urlconn.setDefaultUseCaches(false);
                        if (poststr.equals("") && (m_postbody == null))
                            urlconn.setRequestMethod("GET");
                        else {
                            if (m_postbody == null)
                                m_postbody = poststr.getBytes();
                            urlconn.addRequestProperty("Content-Length", m_postbody.length + "");
                            urlconn.setRequestMethod("POST");
                            urlconn.setDoOutput(true);
                            final byte[] bytebuf = m_postbody;
                            if ((bytebuf != null) && (bytebuf.length > 0)) {
                                out = urlconn.getOutputStream();
                                if (out != null) {
                                    out.write(bytebuf);
                                    out.flush();
                                    out.close();
                                    out = null;
                                }
                            }
                        }
                        urlconn.connect();
                        m_result = urlconn.getResponseCode();
                        collectResponseHeader(urlconn);
                        m_responsemessage = urlconn.getResponseMessage();
                        in = urlconn.getInputStream();
                        if (in != null) {
                            if (readbody == false) {

                                int len = Primitive.parseInt(urlconn.getHeaderField("Content-Length"), 0);
                                if (len < 0)
                                    len = 0xFFFFFFF;
                                final byte[] buffer = new byte[102400];
                                m_responsebodysize = 0;
                                for (int num = 0; (num = in.read(buffer)) > 0; ) {
                                    m_responsebodysize += num;
                                }
                                in.close();
                                in = null;
                            } else {
                                int len = Primitive.parseInt(urlconn.getHeaderField("Content-Length"), 0);
                                if (len < 0)
                                    len = 0xFFFFFFF;
                                brbuf = new ByteArrayOutputStream();
                                final byte[] buffer = new byte[2048];
                                for (int num = 0; (num = in.read(buffer)) > 0; ) {
                                    brbuf.write(buffer, 0, num);
                                    if (brbuf.size() >= len)
                                        break;
                                }
                                in.close();
                                in = null;
                            }
                        }
                        urlconn.disconnect();
                        urlconn = null;
                    }
                }
            } catch (final Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (brbuf != null) {
                    if (brbuf.size() > 0)
                        m_pagebody = brbuf.toByteArray();
                    try {
                        brbuf.close();
                    } catch (final IOException e) {
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (final IOException e) {
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (final IOException e) {
                    }
                }
                if (urlconn != null)
                    urlconn.disconnect();
            }
        }
        return m_result;
    }

    /**
     * 设置请求头信息
     *
     * @param urlconn
     *         httpURLconnection
     * @param header
     *         头信息
     */
    private void doWithHeader(final HttpURLConnection urlconn, final Map<String, String> header) {
        boolean haveua = false;
        if ((urlconn != null) && (header != null) && (header.size() > 0)) {
            final Iterator<String> it = header.keySet().iterator();
            if (it != null) {
                while (it.hasNext()) {
                    final String name = it.next();
                    if ((name != null) && !name.equals("")) {
                        final String value = header.get(name);
                        if ((value != null) && !value.equals("")) {
                            if (name.toLowerCase().equals("user-agent"))
                                haveua = true;
                            urlconn.addRequestProperty(name, value);
                        }
                    }
                }
            }
        }
        if (!haveua)
            urlconn.addRequestProperty("User-Agent", Mammoth_Ua);
    }

    /**
     * 设置get请求参数
     * @param url 请求URL
     * @param par get请求参数集合
     *
     * @return
     */
    private String fixUrl(String url, final Map<String, String> par) {
        final String parstr = getPar(par);
        if (!parstr.equals("")) {
            final int npos = url.indexOf('?');
            if (npos > 0) {
                if (!url.endsWith("?"))
                    url += "&";
            } else
                url += "?";
            url += parstr;
        }
        return url;
    }

    /**
     * 获得目标URL反馈的二进制文件体
     *
     * @return 文件体
     */
    public byte[] getBody() {
        return m_pagebody;
    }

    /**
     * 获取响应内容
     * @return
     */
    public String getBodyString() {
        return this.getBodyString("UTF-8");
    }

    /**
     * 获取响应内容
     * @param encode 编码格式
     * @return
     */
    public String getBodyString(final String encode) {
        if (m_pagebody != null) {
            if (encode != null) {
                try {
                    return new String(m_pagebody, encode);
                } catch (final UnsupportedEncodingException e) {
                }
            } else
                return new String(m_pagebody);
        }
        return null;
    }

    /**
     * 获得设置过的GET协议的参数值
     *
     * @param key
     *         参数名称
     *
     * @return 参数值
     */
    public String getGetPar(final String key) {
        return m_getpar == null ? null : (String) m_getpar.get(key);
    }

    /**
     * 获得目标URL反馈的HEAD的MAP对象
     *
     * @return MAP对象
     */
    public Map<String, List<String>> getHeader() {
        return m_responseheader;
    }

    /**
     * 获得目标URL反馈的HEAD的参数值
     *
     * @param key
     *         参数名称
     *
     * @return 能找到的第一个此参数的参数值
     */
    public String getHeader(final String key) {
        return getHeader(key, 0);
    }

    /**
     * 获得目标URL反馈的HEAD的参数值
     *
     * @param key
     *         参数名称
     * @param index
     *         第几个（可能会反馈回多个同名的参数）
     *
     * @return 参数值
     */
    public String getHeader(final String key, final int index) {
        if (m_responseheader != null) {
            final List<String> s = m_responseheader.get(key);
            if (s != null) {
                if (index < s.size())
                    return s.get(index);
            }
        }
        return null;
    }

    /**
     * 获得目标URL反馈的HEAD的参数名称数组
     *
     * @return 参数名称数组
     */
    public String[] getHeaderNames() {
        if ((m_responseheader != null) && (m_responseheader.size() > 0)) {
            final String[] list = new String[m_responseheader.size()];
            m_responseheader.keySet().toArray(list);
            return list;
        }
        return null;
    }

    /**
     * 获得访问时产生的一些消息
     *
     * @return 消息内容
     */
    public String getMessage() {
        return m_responsemessage;
    }

    /**
     * 组织请求参数字符串
     * @param m 请求参数集合
     * @return
     */
    private String getPar(final Map<String, String> m) {
        if ((m != null) && (m.size() > 0)) {
            final StringBuffer par = new StringBuffer();
            final Iterator<String> it = m.keySet().iterator();
            if (it != null) {
                while (it.hasNext()) {
                    final String name = it.next();
                    if ((name != null) && !name.equals("")) {
                        final String value = m.get(name);
                        if ((value != null) && !value.equals(""))
                            par.append("&").append(name).append("=").append(UrlUtil.encodeUrl(value));
                    }
                }
            }
            if (par.length() > 0)
                return par.substring(1).toString();
        }
        return "";
    }

    /**
     * 获得曾经设置过的POST的参数
     *
     * @param key
     *         参数名称
     *
     * @return 参数值
     */
    public String getPostPar(final String key) {
        return m_postpar == null ? null : (String) m_postpar.get(key);
    }

    /**
     * 获取响应内容大小
     * @return
     */
    public int getResponseBodySize() {
        return m_responsebodysize;
    }

    /**
     * 获得目标URL反馈的回应值
     *
     * @return 回应值（一般应为200）
     */
    public int getResult() {
        return m_result;
    }

    /**
     * 设置http验证字符串
     *
     * @param authorization
     *         验证字符串
     */
    public void setAuthorization(final String authorization) {
        if (authorization != null) {
            m_authorization = authorization;
            if (!m_authorization.toLowerCase().startsWith("basic "))
                m_authorization = "Basic " + m_authorization;
        }
    }

    /**
     * 设置要POST的二进制流，替代POST的参数
     *
     * @param body
     *         二进制包体
     */
    public void setPostBody(final byte[] body) {
        m_postbody = body;
    }

    /**
     * 设置超时
     *
     * @param conn
     *         连接超时（毫秒）
     * @param read
     *         读超时（毫秒）
     */
    public void setTimeout(final int conn, final int read) {
        m_conntimeout = conn;
        m_readtimeout = read;
    }

    /**
     * 设置访问的目标url
     *
     * @param url
     *         目标URL
     */
    public void setUrl(final String url) {
        m_url = url;
    }

    /**
     * 校验是否为空字符串
     *
     * @param str
     *         目标字符串
     *
     * @return
     */
    private boolean strIsRight(final String str) {
        return (str != null) && !str.equals("");
    }
}
