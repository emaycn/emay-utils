package cn.emay.utils.encryption;

/**
 * 16进制2进制转换工具
 * 
 * @author Frank
 *
 */
public class HexByte {

	/**
	 * 2進制转换为16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String byte2Hex(byte buf[]) {
		if (buf == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 16進制转换为2进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] hex2Byte(String hexStr) {
		if (hexStr == null) {
			return null;
		}
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

}
