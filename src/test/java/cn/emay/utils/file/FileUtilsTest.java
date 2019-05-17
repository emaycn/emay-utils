package cn.emay.utils.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 
 * @author Frank
 *
 */
public class FileUtilsTest {

	public static void main(String[] args) throws FileNotFoundException {

		FileUtils.copyDir("F:\\迅雷下载", "F:\\123");
		FileUtils.delete("F:\\123");
		FileUtils.copyToDir("F:\\迅雷下载", "F:\\123");

		FileUtils.moveDir("F:\\迅雷下载", "F:\\123");
		FileUtils.moveToDir("F:\\123", "F:\\迅雷下载");

		String srcPath = "F:\\迅雷下载\\123\\061618_01\\萨德.txt";
		String toPath = "F:\\迅雷下载\\123\\061618_01\\萨德1.txt";

		FileInputStream in = new FileInputStream(srcPath);
		FileOutputStream out = new FileOutputStream(toPath);

		FileUtils.write(in, toPath);
		FileUtils.read(srcPath, out);

	}

}
