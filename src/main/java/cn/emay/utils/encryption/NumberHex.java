package cn.emay.utils.encryption;

/**
 * 数字进制转换
 * 
 * @author Frank
 *
 */
public class NumberHex {

	/**
	 * 进制数
	 */
	private long hex;

	/**
	 * 进制字符集
	 */
	private char[] chars;

	/**
	 * 进制对应的数字集
	 */
	private Integer[] ints;

	/**
	 * 初始化
	 * 
	 * @param chars
	 *            进制字符集<字符个数即进制数><br/>
	 *            如：['0','1'] 即为二进制
	 */
	public NumberHex(char[] chars) {
		if (chars == null || chars.length == 0) {
			throw new IllegalArgumentException("chars must not null");
		}
		this.chars = chars;
		hex = chars.length;
		ints = new Integer[chars.length];
		for (int i = 0; i < hex; i++) {
			ints[i] = i;
		}
	}

	/**
	 * 根据字符序号拿到字符
	 * 
	 * @param index
	 * @return
	 */
	private Character getCharIndex(int index) {
		return chars[index];
	}

	/**
	 * 根据字符拿到数字
	 * 
	 * @param cha
	 * @return
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
		return ints[index];
	}

	/**
	 * 编码
	 * 
	 * @param number
	 * @return
	 */
	public String encode(long number) {
		if (number <= 0) {
			return new String(new char[] { chars[0] });
		}
		Long rest = number;
		char[] stac = new char[64];
		int i = 64;
		while (rest != 0) {
			Long res = rest / hex;
			int env = new Long(rest % hex).intValue();
			Character cha = getCharIndex(env);
			stac[--i] = cha;
			rest = res;
		}
		return new String(stac, i, (64 - i));
	}

	/**
	 * 解码
	 * 
	 * @param inhex
	 * @return
	 */
	public long decode(String inhex) {
		if (inhex == null || inhex.trim().length() == 0) {
			return 0l;
		}
		long result = 0l;
		char[] chars = inhex.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char cha = chars[i];
			Integer ind = getIndexChar(cha);
			if (ind == null) {
				return 0l;
			}
			result += Math.pow(hex, chars.length - i - 1) * ind;
		}
		return result;
	}

}
