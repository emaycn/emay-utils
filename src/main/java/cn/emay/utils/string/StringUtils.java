package cn.emay.utils.string;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * string 工具类
 *
 * @author Frank
 */
public class StringUtils {

    /**
     * 去除前后空
     *
     * @param source 字符串
     * @return 处理后的字符串
     */
    public static String trim(String source) {
        return source != null ? source.trim() : null;
    }

    /**
     * 是否空
     *
     * @param source 字符串
     * @return 是否空
     */
    public static boolean isEmpty(String source) {
        return source == null || source.length() == 0;
    }

    /**
     * 是否不为空
     *
     * @param source 字符串
     * @return 是否不为空
     */
    public static boolean isNotEmpty(String source) {
        return !isEmpty(source);
    }

    /**
     * 是否无信息
     *
     * @param source 字符串
     * @return 是否无信息
     */
    public static boolean isBlank(String source) {
        return !isNotBlank(source);
    }

    /**
     * 是否有信息
     *
     * @param source 字符串
     * @return 是否有信息
     */
    public static boolean isNotBlank(String source) {
        return source != null && source.length() > 0 && source.trim().length() > 0;
    }

    /**
     * 是否数字
     *
     * @param str 字符串
     * @return 是否数字
     */
    public static boolean isNumeric(String str) {
        return isNotBlank(str) && str.trim().matches("\\d+");
    }

    /**
     * 是否布尔
     *
     * @param str 字符串
     * @return 是否布尔
     */
    public static boolean isBool(String str) {
        return isNotBlank(str) && ("true".equals(str.trim()) || "false".equals(str.trim()));
    }

    /**
     * 是否相等
     *
     * @param src  字符串
     * @param dest 目标字符串
     * @return 是否相等
     */
    public static boolean isEquals(String src, String dest) {
        return Objects.equals(src, dest);
    }

    /**
     * 是否不相等
     *
     * @param src  字符串
     * @param dest 目标字符串
     * @return 是否不相等
     */
    public static boolean isNotEquals(String src, String dest) {
        return !isEquals(src, dest);
    }

    /**
     * 转成boolean
     *
     * @param src 字符串
     * @return boolean
     */
    public static boolean toBoolean(String src) {
        return Boolean.parseBoolean(src);
    }

    /**
     * 转成int
     *
     * @param src          字符串
     * @param defaultValue 默认值
     * @return int
     */
    public static int toInteger(String src, int defaultValue) {
        int value = defaultValue;
        try {
            value = Integer.parseInt(src.trim());
        } catch (Exception ignored) {
        }
        return value;
    }

    /**
     * 转成long
     *
     * @param src          字符串
     * @param defaultValue 默认值
     * @return long
     */
    public static long toLong(String src, long defaultValue) {
        long value = defaultValue;
        try {
            value = Long.parseLong(src.trim());
        } catch (Exception ignored) {
        }
        return value;
    }

    /**
     * 转成double
     *
     * @param src          字符串
     * @param defaultValue 默认值
     * @return double
     */
    public static double toDouble(String src, double defaultValue) {
        double value = defaultValue;
        try {
            value = Double.parseDouble(src.trim());
        } catch (Exception ignored) {
        }
        return value;
    }

    /**
     * 拼接数组
     *
     * @param array 字符串数组
     * @param split 分隔符
     * @return 字符串
     */
    public static String join(String[] array, String split) {
        if (array == null || array.length == 0 || split == null) {
            return null;
        }
        return join(Arrays.asList(array), split);
    }

    /**
     * 拼接集合
     *
     * @param collection 字符串集合
     * @param split      分隔符
     * @return 字符串
     */
    public static String join(Collection<String> collection, String split) {
        if (collection == null || collection.size() == 0 || split == null) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        for (String item : collection) {
            buffer.append(item).append(split);
        }
        return buffer.substring(0, buffer.length() - 1);
    }

}
