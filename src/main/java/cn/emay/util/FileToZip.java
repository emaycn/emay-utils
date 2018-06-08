package cn.emay.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 将文件夹下面的文件 打包成zip压缩文件
 * 
 */
public final class FileToZip {

	/**
	 * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
	 * 
	 * @param sourceFilePath
	 *            :待压缩的文件路径
	 * @param zipFilePath
	 *            :压缩后存放路径
	 * @param fileName
	 *            :压缩后文件的名称
	 * @param isDrop
	 *            :是否删除原文件:true删除、false不删除
	 * @return
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName, Boolean isDrop) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		if (!sourceFile.exists()) {
			System.out.println("待压缩的文件：" + sourceFilePath + "不存在.");
		} else {
			File zipFile = new File(zipFilePath + File.separator + fileName + ".zip");
			if (zipFile.exists()) {
				System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
			} else {
				File[] sourceFiles = null;
				if (sourceFile.isFile()) {
					sourceFiles = new File[] { sourceFile };
				} else if (sourceFile.isDirectory()) {
					sourceFiles = sourceFile.listFiles();
				}
				if (null == sourceFiles || sourceFiles.length < 1) {
					System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
				} else {
					try {
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(new BufferedOutputStream(fos));
						byte[] bufs = new byte[1024 * 10];
						for (int i = 0; i < sourceFiles.length; i++) {
							// 创建ZIP实体，并添加进压缩包
							ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
							zos.putNextEntry(zipEntry);
							// 读取待压缩的文件并写进压缩包里
							fis = new FileInputStream(sourceFiles[i]);
							bis = new BufferedInputStream(fis, 1024 * 10);
							int read = 0;
							while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
								zos.write(bufs, 0, read);
							}
						}
						flag = true;
					} catch (FileNotFoundException e) {
						throw new RuntimeException(e);
					} catch (IOException e) {
						throw new RuntimeException(e);
					} finally {
						// 关闭流
						try {
							if (null != bis)
								bis.close();
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						try {
							if (null != zos)
								zos.close();
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						try {
							if (null != fos)
								fos.close();
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						try {
							if (null != fis)
								fis.close();
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
					if (isDrop) {
						boolean isSuccess = true;
						for (int j = 0; j < sourceFiles.length; j++) {
							if (sourceFiles[j].exists()) {
								isSuccess = sourceFiles[j].delete();
								if (!isSuccess) {
									System.out.println("删除源文件失败：" + sourceFiles[j].getName());
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		String sourceFilePath = "E:\\exportExcel\\file\\充值扣费明细.xlsx";
		String zipFilePath = "E:\\exportExcel\\zip";
		String fileName = "充值扣费明细";
		boolean flag = FileToZip.fileToZip(sourceFilePath, zipFilePath, fileName, true);
		if (flag) {
			System.out.println("文件打包成功!");
		} else {
			System.out.println("文件打包失败!");
		}
	}

}