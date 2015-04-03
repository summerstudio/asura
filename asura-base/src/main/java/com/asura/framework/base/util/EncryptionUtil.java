/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file EncryptionUtil 
 * @package com.asura.framework.base.util 
 *
 * @date 2015/3/18 14:21 
 */
package com.asura.framework.base.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p> 加密工具 </P>
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
public class EncryptionUtil {

    public static final String ALGORITHM_MD5 = "MD5";

    public static final String ALGORITHM_SHA_1 = "SHA-1";

    public static final String ENCODING = "UTF-8";

    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * md5或者sha-1加密
     *
     * @param inputText
     *         要加密的内容
     * @param algorithm
     *         加密算法名称：md5或者sha-1，不区分大小写
     *         MessageDigest Algorithms
     *         The algorithm names in this section can be specified when generating an instance of MessageDigest.
     *
     *         Algorithm Name	Description
     *         MD2	The MD2 message digest algorithm as defined in RFC 1319.
     *         MD5	The MD5 message digest algorithm as defined in RFC 1321.
     *         SHA-1        Hash algorithms defined in the FIPS PUB 180-2.
     *         SHA-256      SHA-256 is a 256-bit hash function intended to provide 128 bits of security against collision attacks,
     *         SHA-384      while SHA-512 is a 512-bit hash function intended to provide 256 bits of security.
     *         SHA-512      A 384-bit hash may be obtained by truncating the SHA-512 output.
     *
     * @return
     */
    public static String encrypt(String inputText, String algorithm, String encoding) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithm == null || "".equals(algorithm.trim())) {
            algorithm = ALGORITHM_MD5;
        }
        if (encoding == null || "".equals(encoding.trim())) {
            encoding = ENCODING;
        }
        String encryptText = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputText.getBytes(encoding));
            byte[] encryptBytes = messageDigest.digest();
            encryptText = byteArrayToHexString(encryptBytes);
            return encryptText;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes
     *         the raw bytes from the digest.
     *
     * @return the formatted bytes.
     */
    private static String byteArrayToHexString(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        String text = "123asd";
        String s = EncryptionUtil.encrypt(text, "MD5", null);
        System.out.println(s.length() + "=" + s);
        String s1 = EncryptionUtil.encrypt(text, "SHA-1", null);
        System.out.println(s1.length() + "=" + s1);
    }
}
