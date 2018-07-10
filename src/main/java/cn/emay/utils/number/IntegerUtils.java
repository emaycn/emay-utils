package cn.emay.utils.number;

public class IntegerUtils {

	/**
	 * int 到4位字节
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] toBytes(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 字节转int
	 * 
	 * @param src
	 * @return
	 */
	public static int toInt(byte[] src) {
		return (int) (((src[0] & 0xFF) << 24) | ((src[1] & 0xFF) << 16) | ((src[2] & 0xFF) << 8) | (src[3] & 0xFF));
	}
	
}
