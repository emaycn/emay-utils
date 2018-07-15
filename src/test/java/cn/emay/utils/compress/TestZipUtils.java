package cn.emay.utils.compress;

import cn.emay.utils.compress.ZipUtils;

public class TestZipUtils {

	public static void main(String[] args) {
		ZipUtils.zip("C:\\Users\\Frank\\Desktop\\规范", "C:\\Users\\Frank\\Desktop\\emay.zip");
		ZipUtils.unzip("C:\\Users\\Frank\\Desktop\\emay.zip", "C:\\Users\\Frank\\Desktop\\111");
	}

	public void testZip() {

	}

}
