package cn.emay.utils.compress;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.compress.GZIPUtils;

public class TestGzipUtils {
	
	@Test
	public void testGizp() {
		String st = "sfdsfk203ois_+!O@_#)#!*$(^#$(&@#)__+@";
		byte[] b = GZIPUtils.compress(st.getBytes());
		b = GZIPUtils.decompress(b);
		Assert.assertEquals(st, new String(b));
		
	}

}
