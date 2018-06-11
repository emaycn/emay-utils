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
		if (checkString == null)
			return false;
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(checkString);
		return m.matches();
	}

}
