package cn.emay.utils.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Frank
 */
public class StringUtilsTest {

    @Test
    public void test() {
        Assert.assertTrue(StringUtils.isBool("true"));
        Assert.assertFalse(StringUtils.isBool("1"));
    }

}
