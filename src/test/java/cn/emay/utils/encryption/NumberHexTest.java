package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.NumberHex;

/**
 * 
 * @author Frank
 *
 */
public class NumberHexTest {

	@Test
	public void testNumberHex() throws UnsupportedEncodingException {
		NumberHex hex = new NumberHex("01234".toCharArray());
		int number = 123456;
		String s = hex.encode(number);
		long s1 = hex.decode(s);
		Assert.assertEquals(number, s1);
	}

}
