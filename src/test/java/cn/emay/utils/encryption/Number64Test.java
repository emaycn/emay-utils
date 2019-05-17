package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.Number64;

/**
 * 
 * @author Frank
 *
 */
public class Number64Test {

	@Test
	public void testNumber64() throws UnsupportedEncodingException {

		int number = 123456;
		String s = Number64.encode(number);
		long s1 = Number64.decode(s);
		Assert.assertEquals(number, s1);

	}

}
