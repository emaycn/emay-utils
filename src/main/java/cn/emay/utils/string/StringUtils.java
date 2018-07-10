package cn.emay.utils.string;

/**
 * string 工具类
 * 
 * @author Frank
 *
 */
public class StringUtils {

	/**
	 * 去除前后空
	 * 
	 * @param source
	 * @return
	 */
	public static String trim(String source) {
		return source != null ? source.trim() : source;
	}

	/**
	 * 是否空
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String source) {
		return source == null || source.length() == 0;
	}

	/**
	 * 是否不为空
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isNotEmpty(String source) {
		return !isEmpty(source);
	}

	/**
	 * 是否无信息
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isBlank(String source) {
		return source == null || source.length() <= 0 || source.trim().length() <= 0;
	}

	/**
	 * 是否有信息
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isNotBlank(String source) {
		return !isBlank(source);
	}

	/**
	 * 是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		return !isBlank(str) && str.trim().matches("\\d+");
	}

	/**
	 * 是否布尔
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBool(String str) {
		return !isBlank(str) && (str.trim().equals("true") || str.trim().equals("false"));
	}

	/**
	 * 是否相等
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static boolean isEquals(String src, String dest) {
		return src == null && dest == null ? true : (src != null && dest != null ? src.trim().equals(dest.trim()) : false);
	}

	/**
	 * 是否不相等
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static boolean isNotEquals(String src, String dest) {
		return !isEquals(src, dest);
	}

	/**
	 * 转成boolean
	 * 
	 * @param src
	 * @return
	 */
	public static boolean toBoolean(String src) {
		return Boolean.valueOf(src);
	}

	/**
	 * 转成int
	 * 
	 * @param src
	 * @param defaultValue
	 * @return
	 */
	public static int toInteger(String src, int defaultValue) {
		int value = defaultValue;
		try {
			value = Integer.valueOf(src.trim());
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 转成long
	 * 
	 * @param src
	 * @param defaultValue
	 * @return
	 */
	public static long toLong(String src, long defaultValue) {
		long value = defaultValue;
		try {
			value = Long.valueOf(src.trim());
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 转成double
	 * 
	 * @param src
	 * @param defaultValue
	 * @return
	 */
	public static double toDouble(String src, double defaultValue) {
		double value = defaultValue;
		try {
			value = Double.valueOf(src.trim());
		} catch (Exception e) {
		}
		return value;
	}

}
