package cn.emay.utils.encryption;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Frank
 */
public class Base64Test {

    @Test
    public void testBase64() {

        String content = "stttte31sdfs问问发顺丰的./;'()*(#^$@*#^$_~";

        String s = Base64.encode(content.getBytes(StandardCharsets.UTF_8));
        String s1 = Base64.encode(content);
        String s2 = Base64.encode(content, "UTF-8");
        byte[] end1 = Base64.decode(s);
        String end2 = Base64.decode(s1, "UTF-8");
        String end3 = Base64.decodeUTF8(s2);

        Assert.assertEquals(content, new String(end1, StandardCharsets.UTF_8));
        Assert.assertEquals(content, end2);
        Assert.assertEquals(content, end3);

    }

}
