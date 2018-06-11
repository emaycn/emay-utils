package cn.emay.utils;

import cn.emay.utils.compress.ZipUtils;

public class TestZipUtils {
	
	public static void main(String[] args) {
		ZipUtils.zip("C:\\Users\\Frank\\Desktop\\emay", "C:\\Users\\Frank\\Desktop\\emay.zip");
		ZipUtils.unzip("C:\\Users\\Frank\\Desktop\\emay.zip", "C:\\Users\\Frank\\Desktop\\emay1");
	}
	
	public void testZip() {
		
	}

}
