package cn.emay.utils.encryption;

/**
 * 数字进制转换
 *
 * @author Frank
 */
public class NumberHex {

    /**
     * 进制数
     */
    private final long hex;

    /**
     * 进制字符集
     */
    private final char[] chars;

    /**
     * 进制对应的数字集
     */
    private final Integer[] intArray;

    /**
     * 初始化
     *
     * @param chars 进制字符集<字符个数即进制数><br/>
     *              如：['0','1'] 即为二进制
     */
    public NumberHex(char[] chars) {
        if (chars == null || chars.length == 0) {
            throw new IllegalArgumentException("chars must not null");
        }
        this.chars = chars;
        hex = chars.length;
        intArray = new Integer[chars.length];
        for (int i = 0; i < hex; i++) {
            intArray[i] = i;
        }
    }

    /**
     * 根据字符序号拿到字符
     *
     * @param index 字符序号
     * @return 字符
     */
    private Character getCharIndex(int index) {
        return chars[index];
    }

    /**
     * 根据字符拿到数字
     *
     * @param cha 字符
     * @return 序号
     */
    private Integer getIndexChar(char cha) {
        int index = -1;
        for (int i = 0; i < hex; i++) {
            if (chars[i] == cha) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        }
        return intArray[index];
    }

    /**
     * 编码
     *
     * @param number 10进制数字
     * @return n进制数字
     */
    public String encode(long number) {
        if (number <= 0) {
            return new String(new char[]{chars[0]});
        }
        long rest = number;
        char[] chars = new char[64];
        int i = 64;
        while (rest != 0) {
            long res = rest / hex;
            int env = new Long(rest % hex).intValue();
            Character cha = getCharIndex(env);
            chars[--i] = cha;
            rest = res;
        }
        return new String(chars, i, (64 - i));
    }

    /**
     * 解码
     *
     * @param inHex n进制数字
     * @return 10进制数字
     */
    public long decode(String inHex) {
        if (inHex == null || inHex.trim().length() == 0) {
            return 0L;
        }
        long result = 0L;
        char[] chars = inHex.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char cha = chars[i];
            Integer ind = getIndexChar(cha);
            if (ind == null) {
                return 0L;
            }
            result += Math.pow(hex, chars.length - i - 1) * ind;
        }
        return result;
    }

}
