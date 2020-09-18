package cn.emay.utils.compress;

/**
 * @author Frank
 */
public class ZipUtilsTest {

    public static void main(String[] args) {
        ZipUtils.zip("C:\\Users\\Frank\\Desktop\\规范", "C:\\Users\\Frank\\Desktop\\emay.zip");
        ZipUtils.unzip("C:\\Users\\Frank\\Desktop\\emay.zip", "C:\\Users\\Frank\\Desktop\\111");
    }

}
