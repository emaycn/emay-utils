package cn.emay.utils.compress;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.compress.GzipUtils;

/**
 * 
 * @author Frank
 *
 */
public class GzipUtilsTest {

	@Test
	public void testGizp() {
		String st = "sfdsfk203ois_+!O@_#)#!*$(^#$(&@#)__+@";
		byte[] b = GzipUtils.compress(st.getBytes());
		b = GzipUtils.decompress(b);
		Assert.assertEquals(st, new String(b));

	}

}
