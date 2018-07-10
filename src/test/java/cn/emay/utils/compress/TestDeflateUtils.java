package cn.emay.utils.compress;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.compress.DeflateUtils;

public class TestDeflateUtils {

	@Test
	public void testGizp() {
		String st = "sfdsfk203ois_+!O@_#)#!*$(^#$(&@#)__+@";
		byte[] b = DeflateUtils.compress(st.getBytes(), 1);
		b = DeflateUtils.decompress(b);
		Assert.assertEquals(st, new String(b));

	}

}
