package cn.emay.utils;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.NumberHex;

public class TestNumberHex {

	@Test
	public void testNumberHex() throws UnsupportedEncodingException {
		NumberHex hex = new NumberHex("01234".toCharArray());
		int number = 123456;
		String s = hex.encode(number);
		long s1 = hex.decode(s);
		Assert.assertEquals(number, s1);
	}

}
