package cn.emay.utils;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.AES;

public class TestAES {

	@Test
	public void testAES() throws UnsupportedEncodingException {

		String content = "stttte31";
		String password = "0123456789012345";
		String ivStr = "0123456789012345";

		byte[] end1 = AES.encrypt(content, password);
		byte[] end2 = AES.encrypt(content, password, "UTF-8");
		byte[] end3 = AES.encrypt(content.getBytes("UTF-8"), password.getBytes("UTF-8"));
		byte[] end4 = AES.encrypt(content.getBytes("UTF-8"), password.getBytes("UTF-8"), AES.ALGORITHM_AEPP);
		byte[] end5 = AES.encrypt(content.getBytes("UTF-8"), password.getBytes("UTF-8"), ivStr.getBytes("UTF-8"), "AES/CBC/PKCS5Padding");

		byte[] content1 = AES.decrypt(end1, password);
		byte[] content2 = AES.decrypt(end2, password.getBytes("UTF-8"));
		byte[] content3 = AES.decrypt(end3, password.getBytes("UTF-8"), AES.ALGORITHM_AEPP);
		byte[] content4 = AES.decrypt(end4, password, "UTF-8");
		byte[] content5 = AES.decrypt(end5, password.getBytes("UTF-8"), ivStr.getBytes("UTF-8"), "AES/CBC/PKCS5Padding");

		Assert.assertEquals(content, new String(content1, "UTF-8"));
		Assert.assertEquals(content, new String(content2, "UTF-8"));
		Assert.assertEquals(content, new String(content3, "UTF-8"));
		Assert.assertEquals(content, new String(content4, "UTF-8"));
		Assert.assertEquals(content, new String(content5, "UTF-8"));

	}

}
