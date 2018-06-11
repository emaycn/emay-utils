package cn.emay.utils.compress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Deflate 压缩工具
 * @author Frank
 *
 */
public class DeflateUtils {

	/**
	 * 
	 * @param inputByte
	 *            待解压缩的字节数组
	 * @return 解压缩后的字节数组
	 * @throws IOException
	 * @throws DataFormatException 
	 */
	public static byte[] decompress(byte[] inputByte) throws IOException, DataFormatException {
		int len = 0;
		Inflater infl = new Inflater();
		infl.setInput(inputByte);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] outByte = new byte[1024];
		try {
			while (!infl.finished()) {
				len = infl.inflate(outByte);
				if (len == 0) {
					break;
				}
				bos.write(outByte, 0, len);
			}
			infl.end();
		} finally {
			bos.close();
		}
		return bos.toByteArray();
	}

	/**
	 * 压缩.
	 * 
	 * @param inputByte
	 *            待压缩的字节数组
	 * @return 压缩后的数据
	 * @throws IOException
	 */
	public static byte[] compress(byte[] inputByte,int level) throws IOException {
		int len = 0;
		Deflater defl = new Deflater();
		defl.setLevel(level);
		defl.setInput(inputByte);
		defl.finish();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] outputByte = new byte[1024];
		try {
			while (!defl.finished()) {
				// 压缩并将压缩后的内容输出到字节输出流bos中
				len = defl.deflate(outputByte);
				bos.write(outputByte, 0, len);
			}
			defl.end();
		} finally {
			bos.close();
		}
		return bos.toByteArray();
	}

}