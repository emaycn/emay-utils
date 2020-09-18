package cn.emay.utils.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具
 *
 * @author frank
 */
public class RegularUtils {


    /**
     * 特殊字符转义
     *
     * @param value 字符串
     * @return 转义后的字符串
     */
    public static String specialCodeEscape(String value) {
        if (null != value && !"".equals(value)) {
            value = value.replace("%", "\\%").replace("_", "\\_");
        }
        return value;
    }

    /**
     * 是否匹配
     *
     * @param reg 正则
     * @param val 检测字符串
     * @return 是否匹配
     */
    public static boolean matches(String reg, String val) {
        if (null == val || "".equals(val)) {
            return false;
        }
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(val);
        return m.matches();
    }

    /**
     * 验证只含有中文和英文
     *
     * @param val 值
     * @return 是否只含有中文和英文
     */
    public static boolean checkString(String val) {
        return matches("^[\u2E80-\u9FFFa-zA-Z]+$", val);
    }

    /**
     * 校验是否包含特殊字符
     *
     * @return true 不包含
     */
    public static boolean notExistSpecial(String val) {
        return matches("[\u4e00-\u9fa5\\w]+", val);
    }

}
