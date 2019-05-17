package cn.emay.utils.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具
 * 
 * @author
 *
 */
public class RegularUtils {

	/**
	 * 是否匹配
	 * 
	 * @param reg
	 * @param checkString
	 * @return
	 */
	public static boolean matches(String reg, String checkString) {
		if (checkString == null) {
			return false;
		}
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(checkString);
		return m.matches();
	}

	/**
	 *
	 * @Title: specialCodeEscape
	 * @Description: 特殊字符转义
	 * @param @param
	 *            value
	 * @param @return
	 * @return String
	 */
	public static String specialCodeEscape(String value) {
		if (null != value && !"".equals(value)) {
			value = value.replace("%", "\\%").replace("_", "\\_");
		}
		return value;
	}

	/**
	 * 验证只含有中文和英文
	 *
	 * @param val
	 * @return
	 */
	public static boolean checkString(String val) {
		if (null == val || "".equals(val)) {
			return false;
		}
		String regExp = "^[\u2E80-\u9FFFa-zA-Z]+$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(val);
		return m.matches();
	}

	/**
	 * 校验是否包含特殊字符
	 *
	 * @return false 包含
	 * @return true 不包含
	 */
	public static boolean notExistSpecial(String value) {
		String regExp = "[\u4e00-\u9fa5\\w]+";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(value);
		return m.matches();
	}

}
