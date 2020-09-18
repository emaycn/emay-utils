package cn.emay.utils.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5特征码工具
 *
 * @author Frank
 */
public class Md5 {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * MD5
     *
     * @param content 内容
     * @return 特征码
     */
    public static String md5(String content) {
        return md5(content, "UTF-8");
    }

    /**
     * MD5
     *
     * @param content 内容
     * @param charSet 编码集[utf-8,gbk......]
     * @return 特征码
     */
    public static String md5(String content, String charSet) {
        try {
            return md5(content.getBytes(charSet));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * MD5
     *
     * @param bytes 数据
     * @return 特征码
     */
    public static String md5(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String s;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        md.update(bytes);
        byte[] tmp = md.digest();
        char[] str = new char[16 * 2];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            byte byte0 = tmp[i];
            str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
            str[k++] = HEX_DIGITS[byte0 & 0xf];
        }
        s = new String(str);
        return s.toUpperCase();
    }

    /**
     * md5For16
     *
     * @param content 内容
     * @return 16位特征码
     */
    public static String md5For16(String content) {
        return md5For16(content, "UTF-8");
    }

    /**
     * md5For16
     *
     * @param content 内容
     * @param charSet 编码集[utf-8,gbk......]
     * @return 16位特征码
     */
    public static String md5For16(String content, String charSet) {
        try {
            return md5For16(content.getBytes(charSet));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * MD5[16位]
     *
     * @param bytes 数据
     * @return 16位特征码
     */
    public static String md5For16(byte[] bytes) {
        String md5 = md5(bytes);
        if (md5 == null) {
            return null;
        }
        return md5(bytes).substring(8, 24);
    }

    /***
     * 获取文件md5
     *
     * @param filepath 文件路径
     */
    public static String md5FileFor16(String filepath) {
        String md5 = md5File(filepath);
        return md5 == null ? null : md5.substring(8, 24);
    }

    /**
     * 获取文件md5
     *
     * @param file 文件
     */
    public static String md5FileFor16(File file) {
        String md5 = md5File(file);
        return md5 == null ? null : md5.substring(8, 24);
    }

    /***
     * 获取文件md5
     *
     * @param filepath 文件路径
     */
    public static String md5File(String filepath) {
        if (filepath == null) {
            return null;
        }
        return md5File(new File(filepath));
    }

    /**
     * 获取文件md5
     *
     * @param file 文件
     */
    public static String md5File(File file) {
        if (file == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] buffer = new byte[2048];
            int len;
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16).toUpperCase();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
