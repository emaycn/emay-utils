package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.Md5;

/**
* 
* @author Frank
*
*/
public class Md5Test {

	@Test
	public void testMd5() throws UnsupportedEncodingException {

		String s = "四大美女我佛慈悲~——）*（*）￥#@#@“？》《》V《MVIUJH";
		String ss = Md5.md5(s);
		String ss1 = Md5.md5(s.getBytes("UTF-8"));
		String ss2 = Md5.md5(s, "UTF-8");

		String sss = Md5.md5For16(s);
		String sss1 = Md5.md5For16(s.getBytes("UTF-8"));
		String sss2 = Md5.md5For16(s, "UTF-8");

		Assert.assertEquals(ss, ss1);
		Assert.assertEquals(ss, ss2);

		Assert.assertEquals(sss, sss1);
		Assert.assertEquals(sss, sss2);

	}

}
