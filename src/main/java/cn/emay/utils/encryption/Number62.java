package cn.emay.utils.encryption;

/**
 * 62进制转换
 *
 * @author Frank
 */
public class Number62 {

    private static final NumberHex HEX = new NumberHex("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray());

    /**
     * 转换为62进制
     *
     * @param number 10进制数字
     * @return 62进制数字
     */
    public static String encode(long number) {
        return HEX.encode(number);
    }

    /**
     * 转换为10进制
     *
     * @param hex62 62进制数字
     * @return 10进制数字
     */
    public static long decode(String hex62) {
        return HEX.decode(hex62);
    }
}