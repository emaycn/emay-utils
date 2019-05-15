package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.Base64;

/**
* 
* @author Frank
*
*/
public class Base64Test {

	@Test
	public void testBase64() throws UnsupportedEncodingException {

		String content = "stttte31sdfs问问发顺丰的./;'()*(#^$@*#^$_~";

		String s = Base64.encode(content.getBytes("UTF-8"));
		String s1 = Base64.encode(content);
		String s2 = Base64.encode(content, "UTF-8");
		byte[] end1 = Base64.decode(s);
		String end2 = Base64.decode(s1, "UTF-8");
		String end3 = Base64.decodeUTF8(s2);

		Assert.assertEquals(content, new String(end1, "UTF-8"));
		Assert.assertEquals(content, end2);
		Assert.assertEquals(content, end3);

	}

}
