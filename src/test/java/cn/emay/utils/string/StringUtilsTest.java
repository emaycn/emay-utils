package cn.emay.utils.string;

import org.junit.Assert;
import org.junit.Test;

/**
* 
* @author Frank
*
*/
public class StringUtilsTest {

	@Test
	public void test() {
		Assert.assertEquals(StringUtils.isBool("true"), true);
		Assert.assertEquals(StringUtils.isBool("1"), false);
	}

}
