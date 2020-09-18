package cn.emay.utils.encryption;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Frank
 */
public class NumberHexTest {

    @Test
    public void testNumberHex() {
        NumberHex hex = new NumberHex("01234".toCharArray());
        int number = 123456;
        String s = hex.encode(number);
        long s1 = hex.decode(s);
        Assert.assertEquals(number, s1);
    }

}
