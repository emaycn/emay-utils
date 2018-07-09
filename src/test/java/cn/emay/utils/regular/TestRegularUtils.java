package cn.emay.utils.regular;

import org.junit.Test;

import cn.emay.utils.regular.RegularUtils;

public class TestRegularUtils {

	@Test
	public void test() {
		RegularUtils.matches("^\\d$", "1");
	}
	
}
