package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5特征码工具
 * 
 * @author Frank
 *
 */
public class Md5 {

	private static char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * MD5
	 * 
	 * @param content
	 *            内容
	 * @return
	 */
	public static String md5(String content) {
		return md5(content, "UTF-8");
	}

	/**
	 * MD5
	 * 
	 * @param content
	 *            内容
	 * @param charSet
	 *            编码集[utf-8,gbk......]
	 * @return
	 */
	public static String md5(String content, String charSet) {
		try {
			return md5(content.getBytes(charSet));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * MD5
	 * 
	 * @param bytes
	 * @return
	 */
	public static String md5(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		String s = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		md.update(bytes);
		byte tmp[] = md.digest();
		char str[] = new char[16 * 2];
		int k = 0;
		for (int i = 0; i < 16; i++) {
			byte byte0 = tmp[i];
			str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
			str[k++] = HEX_DIGITS[byte0 & 0xf];
		}
		s = new String(str);
		return s.toUpperCase();
	}

	/**
	 * md5For16
	 * 
	 * @param content
	 *            内容
	 * @return
	 */
	public static String md5For16(String content) {
		return md5For16(content, "UTF-8");
	}

	/**
	 * md5For16
	 * 
	 * @param content
	 *            内容
	 * @param charSet
	 *            编码集[utf-8,gbk......]
	 * @return
	 */
	public static String md5For16(String content, String charSet) {
		try {
			return md5For16(content.getBytes(charSet));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * MD5[16位]
	 * 
	 * @param bytes
	 * @return
	 */
	public static String md5For16(byte[] bytes) {
		String md5 = md5(bytes);
		if (md5 == null) {
			return null;
		}
		return md5(bytes).substring(8, 24);
	}

}
