package cn.emay.utils.encryption;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import cn.emay.utils.encryption.Aes;

/**
 * 
 * @author Frank
 *
 */
public class AesTest {

	@Test
	public void testAES() throws UnsupportedEncodingException {

		String content = "stttte31";
		String password = "0123456789012345";
		String ivStr = "0123456789012345";

		byte[] end1 = Aes.encrypt(content, password);
		byte[] end2 = Aes.encrypt(content, password, "UTF-8");
		byte[] end3 = Aes.encrypt(content.getBytes("UTF-8"), password.getBytes("UTF-8"));
		byte[] end4 = Aes.encrypt(content.getBytes("UTF-8"), password.getBytes("UTF-8"), Aes.ALGORITHM_AEPP);
		byte[] end5 = Aes.encrypt(content.getBytes("UTF-8"), password.getBytes("UTF-8"), ivStr.getBytes("UTF-8"), "AES/CBC/PKCS5Padding");

		byte[] content1 = Aes.decrypt(end1, password);
		byte[] content2 = Aes.decrypt(end2, password.getBytes("UTF-8"));
		byte[] content3 = Aes.decrypt(end3, password.getBytes("UTF-8"), Aes.ALGORITHM_AEPP);
		byte[] content4 = Aes.decrypt(end4, password, "UTF-8");
		byte[] content5 = Aes.decrypt(end5, password.getBytes("UTF-8"), ivStr.getBytes("UTF-8"), "AES/CBC/PKCS5Padding");

		Assert.assertEquals(content, new String(content1, "UTF-8"));
		Assert.assertEquals(content, new String(content2, "UTF-8"));
		Assert.assertEquals(content, new String(content3, "UTF-8"));
		Assert.assertEquals(content, new String(content4, "UTF-8"));
		Assert.assertEquals(content, new String(content5, "UTF-8"));

	}

}
