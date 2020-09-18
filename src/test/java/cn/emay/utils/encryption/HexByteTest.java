package cn.emay.utils.encryption;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Frank
 */
public class HexByteTest {

    @Test
    public void testBHexByte() {

        byte[] bytes = new byte[]{1, 2, 3, 5, 9, 8, 7, 2, 3, 6, 4, 1, 5, 9};
        String s = HexByte.byte2Hex(bytes);
        byte[] bytes1 = HexByte.hex2Byte(s);

        Assert.assertArrayEquals(bytes, bytes1);

    }

}
