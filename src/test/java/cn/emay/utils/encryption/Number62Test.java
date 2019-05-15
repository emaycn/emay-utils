package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.Number62;

/**
* 
* @author Frank
*
*/
public class Number62Test {

	@Test
	public void testNumber62() throws UnsupportedEncodingException {

		int number = 123456;
		String s = Number62.encode(number);
		long s1 = Number62.decode(s);
		Assert.assertEquals(number, s1);

	}

}
