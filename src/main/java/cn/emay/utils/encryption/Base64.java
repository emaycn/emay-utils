package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * base64编码解码工具
 *
 * @author Frank
 */
public class Base64 {

    private final static char[] BASE64CODE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/',};

    private final static byte[] BASE64DECODE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 63, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1,
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32,
            33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1,};

    /**
     * 编码
     *
     * @param content 内容
     * @return 编码后内容
     */
    public static String encode(String content) {
        return encode(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 编码
     *
     * @param content 内容
     * @param charSet 编码集[utf-8,gbk......]
     * @return 编码后内容
     */
    public static String encode(String content, String charSet) {
        try {
            return encode(content.getBytes(charSet));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 编码
     *
     * @param content 内容
     * @return 编码后内容
     */
    public static String encode(byte[] content) {
        int code = 0;
        StringBuilder sb = new StringBuilder(((content.length - 1) / 3) << 2 + 4);
        for (int i = 0; i < content.length; i++) {
            code |= (content[i] << (16 - i % 3 * 8)) & (0xff << (16 - i % 3 * 8));
            if (i % 3 == 2 || i == content.length - 1) {
                sb.append(BASE64CODE[(code & 0xfc0000) >>> 18]);
                sb.append(BASE64CODE[(code & 0x3f000) >>> 12]);
                sb.append(BASE64CODE[(code & 0xfc0) >>> 6]);
                sb.append(BASE64CODE[code & 0x3f]);
                code = 0;
            }
        }
        if (content.length % 3 > 0) {
            sb.setCharAt(sb.length() - 1, '=');
        }
        if (content.length % 3 == 1) {
            sb.setCharAt(sb.length() - 2, '=');
        }
        return sb.toString();
    }

    /**
     * 解码
     *
     * @param code 编码后的数据
     * @return 原始数据
     */
    public static String decodeUTF8(String code) {
        return decode(code, "UTF-8");
    }

    /**
     * 解码
     *
     * @param code    编码后的数据
     * @param charSet 编码集[utf-8,gbk......]
     * @return 原始数据
     */
    public static String decode(String code, String charSet) {
        try {
            return new String(decode(code), charSet);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 解码
     *
     * @param code 编码后的数据
     * @return 原始数据
     */
    public static byte[] decode(String code) {
        if (code == null) {
            return new byte[0];
        }
        int len = code.length();
        if (len % 4 != 0) {
            throw new IllegalArgumentException("Base64 string length must be 4*n");
        }
        if (code.length() == 0) {
            return new byte[0];
        }
        int pad = 0;
        if (code.charAt(len - 1) == '=') {
            pad++;
        }
        if (code.charAt(len - 2) == '=') {
            pad++;
        }
        int retLen = len / 4 * 3 - pad;
        byte[] ret = new byte[retLen];
        char ch1, ch2, ch3, ch4;
        int i;
        for (i = 0; i < len; i += 4) {
            int j = i / 4 * 3;
            ch1 = code.charAt(i);
            ch2 = code.charAt(i + 1);
            ch3 = code.charAt(i + 2);
            ch4 = code.charAt(i + 3);
            int tmp = (BASE64DECODE[ch1] << 18) | (BASE64DECODE[ch2] << 12) | (BASE64DECODE[ch3] << 6) | (BASE64DECODE[ch4]);
            ret[j] = (byte) ((tmp & 0xff0000) >> 16);
            if (i < len - 4) {
                ret[j + 1] = (byte) ((tmp & 0x00ff00) >> 8);
                ret[j + 2] = (byte) ((tmp & 0x0000ff));
            } else {
                if (j + 1 < retLen) {
                    ret[j + 1] = (byte) ((tmp & 0x00ff00) >> 8);
                }
                if (j + 2 < retLen) {
                    ret[j + 2] = (byte) ((tmp & 0x0000ff));
                }
            }
        }
        return ret;
    }


    public static String safeUrlBase64Encode(byte[] data) {
        String encodeBase64 = encode(data);
        String safeBase64Str = encodeBase64.replace('+', '-');
        safeBase64Str = safeBase64Str.replace('/', '_');
        safeBase64Str = safeBase64Str.replaceAll("=", "");
        return safeBase64Str;
    }

    public static byte[] safeUrlBase64Decode(final String safeBase64Str) {
        String base64Str = safeBase64Str.replace('-', '+');
        base64Str = base64Str.replace('_', '/');
        int mod4 = base64Str.length() % 4;
        if (mod4 > 0) {
            base64Str = base64Str + "====".substring(mod4);
        }
        return decode(base64Str);
    }
}
