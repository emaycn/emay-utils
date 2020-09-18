package cn.emay.utils.encryption;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Frank
 */
public class AesTest {

    @Test
    public void testAes() {

        String content = "你好，你好，";
        String password = "0123456789012345";
        String ivStr = "0123456789012345";

        byte[] end1 = Aes.encrypt(content, password);
        byte[] end2 = Aes.encrypt(content, password, "UTF-8");
        byte[] end3 = Aes.encrypt(content.getBytes(StandardCharsets.UTF_8), password.getBytes(StandardCharsets.UTF_8));
        byte[] end4 = Aes.encrypt(content.getBytes(StandardCharsets.UTF_8), password.getBytes(StandardCharsets.UTF_8), Aes.ALGORITHM_AEPP);
        byte[] end5 = Aes.encrypt(content.getBytes(StandardCharsets.UTF_8), password.getBytes(StandardCharsets.UTF_8), ivStr.getBytes(StandardCharsets.UTF_8), "AES/CBC/PKCS5Padding");

        byte[] content1 = Aes.decrypt(end1, password);
        byte[] content2 = Aes.decrypt(end2, password.getBytes(StandardCharsets.UTF_8));
        byte[] content3 = Aes.decrypt(end3, password.getBytes(StandardCharsets.UTF_8), Aes.ALGORITHM_AEPP);
        byte[] content4 = Aes.decrypt(end4, password, "UTF-8");
        byte[] content5 = Aes.decrypt(end5, password.getBytes(StandardCharsets.UTF_8), ivStr.getBytes(StandardCharsets.UTF_8), "AES/CBC/PKCS5Padding");

        Assert.assertEquals(content, new String(content1, StandardCharsets.UTF_8));
        Assert.assertEquals(content, new String(content2, StandardCharsets.UTF_8));
        Assert.assertEquals(content, new String(content3, StandardCharsets.UTF_8));
        Assert.assertEquals(content, new String(content4, StandardCharsets.UTF_8));
        Assert.assertEquals(content, new String(content5, StandardCharsets.UTF_8));

    }

}
