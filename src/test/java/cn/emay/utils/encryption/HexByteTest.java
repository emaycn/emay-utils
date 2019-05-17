package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.HexByte;

/**
 * 
 * @author Frank
 *
 */
public class HexByteTest {

	@Test
	public void testBHexByte() throws UnsupportedEncodingException {

		byte[] bytes = new byte[] { 1, 2, 3, 5, 9, 8, 7, 2, 3, 6, 4, 1, 5, 9 };
		String s = HexByte.byte2Hex(bytes);
		byte[] bytes1 = HexByte.hex2Byte(s);

		Assert.assertArrayEquals(bytes, bytes1);

	}

}
