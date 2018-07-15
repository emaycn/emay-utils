package cn.emay.utils.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * zip工具
 * 
 * @author Frank
 *
 */
public class ZipUtils {

	/**
	 * 压缩
	 * 
	 * @param srcPath
	 *            待压缩文件/文件夹路径
	 * @param targetZipPath
	 *            压缩文件路径
	 */
	public static void zip(String srcPath, String targetZipPath) {
		if (targetZipPath == null || targetZipPath.length() == 0) {
			throw new IllegalArgumentException("targetZipPath is null");
		}
		File targetZip = new File(targetZipPath);
		if (targetZip.exists()) {
			throw new IllegalArgumentException("targetZipPath : " + targetZipPath + " is exists");
		}
		if (!targetZip.getParentFile().exists()) {
			targetZip.getParentFile().mkdirs();
		}
		FileOutputStream cos = null;
		try {
			cos = new FileOutputStream(targetZip);
			zip(srcPath, cos);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				if (cos != null) {
					cos.close();
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * 压缩<br/>
	 * 
	 * @param srcPath
	 *            待压缩文件/文件夹路径
	 * @param targetOutputStream
	 *            压缩流
	 */
	public static void zip(String srcPath, OutputStream targetOutputStream) {
		if (srcPath == null || srcPath.length() == 0) {
			throw new IllegalArgumentException("srcPath is null");
		}
		if (targetOutputStream == null) {
			throw new IllegalArgumentException("targetOutputStream is null");
		}
		File srcFile = new File(srcPath);
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("srcFilePath : " + srcPath + " is not exists");
		}
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new CheckedOutputStream(targetOutputStream, new CRC32()));
			zipEntry(srcFile.getAbsolutePath(), srcFile, zos);
			zos.flush();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}finally {
			if(zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}
			try {
				targetOutputStream.close();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * 压缩逻辑
	 * 
	 * @param rootPath
	 *            跟路径
	 * @param srcFile
	 *            源文件
	 * @param zos
	 *            输出流
	 */
	private static void zipEntry(String rootPath, File srcFile, ZipOutputStream zos) {
		if (srcFile.isDirectory()) {
			File[] fileList = srcFile.listFiles();
			if (fileList == null || fileList.length == 0) {
				ZipEntry entry = new ZipEntry(srcFile.getAbsolutePath().substring(rootPath.length()) + "/");
				try {
					zos.putNextEntry(entry);
					zos.closeEntry();
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			} else {
				for (File file : fileList) {
					zipEntry(rootPath, file, zos);
				}
			}
		} else {
			BufferedInputStream bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(srcFile));
				ZipEntry entry = new ZipEntry(srcFile.getAbsolutePath().substring(rootPath.length()));
				zos.putNextEntry(entry);
				int size = 1024;
				byte data[] = new byte[1024];
				while ((size = bis.read(data, 0, data.length)) != -1) {
					zos.write(data, 0, size);
				}
				zos.closeEntry();
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
	}

	/**
	 * 解压缩
	 * 
	 * @param srcZipFilePath
	 *            待解压zip文件路径
	 * @param targetDirPath
	 *            解压文件夹路径
	 */
	public static void unzip(String srcZipFilePath, String targetDirPath) {
		if (srcZipFilePath == null || srcZipFilePath.length() == 0) {
			throw new IllegalArgumentException("srcZipFilePath is null");
		}
		if (targetDirPath == null || targetDirPath.length() == 0) {
			throw new IllegalArgumentException("targetDirPath is null");
		}
		File targetFile = new File(targetDirPath);
		if (targetFile.exists() && !targetFile.isDirectory()) {
			throw new IllegalArgumentException("targetDirPath : " + targetDirPath + " is exists , but it is not directory");
		}
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		File srcZip = new File(srcZipFilePath);
		if (!srcZip.exists()) {
			throw new IllegalArgumentException("srcZipFilePath : " + srcZipFilePath + " is not exists");
		}
		String dirPath = targetFile.getAbsolutePath();
		ZipFile zip = null;
		try {
			zip = new ZipFile(srcZip);
			Enumeration<?> entries = zip.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String filePath = dirPath + File.separator + entry.getName();
				File entryFile = new File(filePath);
				if (entry.isDirectory()) {
					entryFile.mkdirs();
				} else {
					if (!entryFile.getParentFile().exists()) {
						entryFile.getParentFile().mkdirs();
					}
					BufferedInputStream bis = null;
					BufferedOutputStream bos = null;
					try {
						bos = new BufferedOutputStream(new FileOutputStream(entryFile));
						bis = new BufferedInputStream(zip.getInputStream(entry));
						int size = 1024;
						byte data[] = new byte[1024];
						while ((size = bis.read(data, 0, data.length)) != -1) {
							bos.write(data, 0, size);
						}
						bos.flush();
					} catch (Exception e) {
						throw new IllegalArgumentException(e);
					} finally {
						try {
							if (bis != null) {
								bis.close();
							}
						} catch (Exception e) {
							throw new IllegalArgumentException(e);
						}
						try {
							if (bos != null) {
								bos.close();
							}
						} catch (Exception e) {
							throw new IllegalArgumentException(e);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				if (zip != null) {
					zip.close();
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * 压缩<br/>
	 * 
	 * @param srcInputStream
	 *            输入流
	 * @param targetOutputStream
	 *            输出流
	 */
	public static void zip(InputStream srcInputStream, OutputStream targetOutputStream) {
		if (srcInputStream == null) {
			throw new IllegalArgumentException("srcInputStream is null");
		}
		if (targetOutputStream == null) {
			throw new IllegalArgumentException("targetOutputStream is null");
		}
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new CheckedOutputStream(targetOutputStream, new CRC32()));
			ZipEntry entry = new ZipEntry("0");
			zos.putNextEntry(entry);
			int size = 1024;
			byte data[] = new byte[1024];
			while ((size = srcInputStream.read(data, 0, data.length)) != -1) {
				zos.write(data, 0, size);
			}
			zos.flush();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				srcInputStream.close();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
			try {
				targetOutputStream.close();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
			if(zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
	}

	/**
	 * 解压缩<br/>
	 * 
	 * @param srcInputStream
	 *            输入流
	 * @param targetOutputStream
	 *            输出流
	 */
	public static void unzip(InputStream srcInputStream, OutputStream targetOutputStream) {
		if (srcInputStream == null) {
			throw new IllegalArgumentException("srcInputStream is null");
		}
		if (targetOutputStream == null) {
			throw new IllegalArgumentException("targetOutputStream is null");
		}
		ZipInputStream zin = null;
		try {
			zin = new ZipInputStream(srcInputStream);
			zin.getNextEntry();
			int size = 1024;
			byte data[] = new byte[1024];
			while ((size = zin.read(data, 0, data.length)) != -1) {
				targetOutputStream.write(data, 0, size);
			}
			targetOutputStream.flush();
			zin.closeEntry();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				srcInputStream.close();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
			try {
				targetOutputStream.close();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
			if(zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
	}

}