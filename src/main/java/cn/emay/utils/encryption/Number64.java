package cn.emay.utils.encryption;

/**
 * 64进制转换
 *
 * @author Frank
 */
public class Number64 {

    final static char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', ':',};

    /**
     * 10进制转换64进制
     *
     * @param number 10进制数字
     * @return 64进制数字
     */
    public static String encode(long number) {
        int shift = 6;
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << shift;
        long mask = radix - 1;
        do {
            buf[--charPos] = DIGITS[(int) (number & mask)];
            number >>>= shift;
        } while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    /**
     * 64进制转换10进制
     *
     * @param deCompStr 64进制数字
     * @return 10进制数字
     */
    public static long decode(String deCompStr) {
        long result = 0;
        for (int i = deCompStr.length() - 1; i >= 0; i--) {
            if (i == deCompStr.length() - 1) {
                result += getCharIndexNum(deCompStr.charAt(i));
                continue;
            }
            for (int j = 0; j < DIGITS.length; j++) {
                if (deCompStr.charAt(i) == DIGITS[j]) {
                    result += ((long) j) << 6 * (deCompStr.length() - 1 - i);
                }
            }
        }
        return result;
    }

    private static long getCharIndexNum(char ch) {
        if ((int) ch >= 48 && (int) ch <= 57) {
            return (int) ch - 48;
        } else if ((int) ch >= 97 && (int) ch <= 122) {
            return (int) ch - 87;
        } else if ((int) ch >= 65 && (int) ch <= 90) {
            return (int) ch - 29;
        } else if ((int) ch == 43) {
            return 62;
        } else if ((int) ch == 47) {
            return 63;
        }
        return 0;
    }

}
