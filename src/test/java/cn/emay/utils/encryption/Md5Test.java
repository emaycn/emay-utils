package cn.emay.utils.encryption;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Frank
 */
public class Md5Test {

    @Test
    public void testMd5() {

        String s = "四大美女我佛慈悲~——）*（*）￥#@#@“？》《》V《MVIUJH";
        String ss = Md5.md5(s);
        String ss1 = Md5.md5(s.getBytes(StandardCharsets.UTF_8));
        String ss2 = Md5.md5(s, "UTF-8");

        String sss = Md5.md5For16(s);
        String sss1 = Md5.md5For16(s.getBytes(StandardCharsets.UTF_8));
        String sss2 = Md5.md5For16(s, "UTF-8");

        Assert.assertEquals(ss, ss1);
        Assert.assertEquals(ss, ss2);

        Assert.assertEquals(sss, sss1);
        Assert.assertEquals(sss, sss2);

    }

}
