package cn.emay.utils.encryption;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Frank
 */
public class Number64Test {

    @Test
    public void testNumber64() {

        int number = 123456;
        String s = Number64.encode(number);
        long s1 = Number64.decode(s);
        Assert.assertEquals(number, s1);

    }

}
