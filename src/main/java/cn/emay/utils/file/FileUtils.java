package cn.emay.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * 
 * 文件工具
 * 
 * @author Frank
 *
 */
public class FileUtils {

	/**
	 * 把文件(夹)复制到指定的文件夹中
	 * 
	 * @param srcFile
	 *            源文件(夹)
	 * @param toDirPath
	 *            目标文件夹
	 */
	public static void copyToDir(String srcFilePath, String toDirPath) {
		if (srcFilePath == null) {
			throw new IllegalArgumentException("srcFile is null");
		}
		File srcFile = new File(srcFilePath);
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("srcFile " + srcFile.getAbsolutePath() + " is not exists");
		}
		if (toDirPath == null) {
			throw new IllegalArgumentException("toDirPath is null");
		}
		File toFile = new File(toDirPath);
		if (!toFile.exists()) {
			toFile.mkdirs();
		}
		copy(srcFile, new File(toFile, srcFile.getName()));
	}

	/**
	 * 把文件夹的所有子文件(夹)复制到目标文件夹中
	 * 
	 * @param srcDirPath
	 *            源文件夹
	 * @param toDirPath
	 *            目标文件夹
	 */
	public static void copyDir(String srcDirPath, String toDirPath) {
		copy(new File(srcDirPath), new File(toDirPath));
	}

	/**
	 * 把文件复制到目标地址
	 * 
	 * @param srcFilePath
	 *            源文件
	 * @param toFilePath
	 *            目标文件
	 */
	public static void copyFile(String srcFilePath, String toFilePath) {
		copy(new File(srcFilePath), new File(toFilePath));
	}

	/**
	 * 把文件(夹)复制到目标文件(夹)地址
	 * 
	 * @param srcFile
	 *            源文件(夹)
	 * @param toFile
	 *            目标文件(夹)
	 */
	public static void copy(File srcFile, File toFile) {
		if (srcFile.isDirectory()) {
			toFile.mkdirs();
			File[] srcs = srcFile.listFiles();
			if (srcs == null || srcs.length == 0) {
				return;
			}
			for (File src : srcs) {
				copy(src, new File(toFile, src.getName()));
			}
		} else {
			if (toFile.exists()) {
				throw new IllegalArgumentException("toFile " + toFile.getAbsolutePath() + " is exists");
			}
			FileInputStream in = null;
			FileOutputStream out = null;
			FileChannel inChannel = null;
			FileChannel outChannel = null;
			try {
				in = new FileInputStream(srcFile);
				out = new FileOutputStream(toFile);
				inChannel = in.getChannel();
				outChannel = out.getChannel();
				int maxCount = 64 * 1024 * 1024;
				long size = inChannel.size();
				long position = 0;
				while (position < size) {
					position += inChannel.transferTo(position, maxCount, outChannel);
				}
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			} finally {
				IOException[] exps = new IOException[4];
				if (inChannel != null) {
					try {
						inChannel.close();
					} catch (IOException e) {
						exps[0] = e;
					}
				}
				if (outChannel != null) {
					try {
						outChannel.close();
					} catch (IOException e) {
						exps[1] = e;
					}
				}
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						exps[2] = e;
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						exps[3] = e;
					}
				}
				for (IOException e : exps) {
					if (e != null) {
						throw new IllegalArgumentException(e);
					}
				}
			}
		}
	}

	/**
	 * 把文件(夹)移动到指定的文件夹中
	 * 
	 * @param srcFile
	 *            源文件(夹)
	 * @param toDirPath
	 *            目标文件夹
	 */
	public static void moveToDir(String srcFilePath, String toDirPath) {
		if (srcFilePath == null) {
			throw new IllegalArgumentException("srcFile is null");
		}
		File srcFile = new File(srcFilePath);
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("srcFile " + srcFile.getAbsolutePath() + " is not exists");
		}
		if (toDirPath == null) {
			throw new IllegalArgumentException("toDirPath is null");
		}
		File toFile = new File(toDirPath);
		if (!toFile.exists()) {
			toFile.mkdirs();
		}
		move(srcFile, new File(toFile, srcFile.getName()));
	}

	/**
	 * 把文件夹的所有子文件(夹)移动到目标文件夹中<br/>
	 * 删除源文件夹
	 * 
	 * @param srcDirPath
	 *            源文件夹
	 * @param toDirPath
	 *            目标文件夹
	 */
	public static void moveDir(String srcDirPath, String toDirPath) {
		move(new File(srcDirPath), new File(toDirPath));
	}

	/**
	 * 把文件移动到目标地址
	 * 
	 * @param srcFilePath
	 *            源文件
	 * @param toFilePath
	 *            目标文件
	 */
	public static void moveFile(String srcFilePath, String toFilePath) {
		move(new File(srcFilePath), new File(toFilePath));
	}

	/**
	 * 把文件(夹)移动到指定的文件(夹)
	 * 
	 * @param srcFile
	 *            源文件(夹)
	 * @param toFile
	 *            目标文件(夹)
	 */
	public static void move(File srcFile, File toFile) {
		if (srcFile.isDirectory()) {
			toFile.mkdirs();
			File[] srcs = srcFile.listFiles();
			if (srcs != null && srcs.length != 0) {
				for (File src : srcs) {
					move(src, new File(toFile, src.getName()));
				}
			}
			srcFile.delete();
		} else {
			if (toFile.exists()) {
				throw new IllegalArgumentException("toFile " + toFile.getAbsolutePath() + " is exists");
			}
			srcFile.renameTo(toFile);
		}
	}

	/**
	 * 删除文件(夹)
	 * 
	 * @param filePath
	 *            文件(夹)
	 */
	public static void delete(String filePath) {
		delete(new File(filePath));
	}

	/**
	 * 删除文件(夹)
	 * 
	 * @param file
	 *            文件(夹)
	 */
	public static void delete(File file) {
		if (file == null || !file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			File[] srcs = file.listFiles();
			if (srcs != null && srcs.length != 0) {
				for (File src : srcs) {
					delete(src);
				}
			}
		}
		file.delete();
	}

	/**
	 * 流写文件<br/>
	 * 
	 * @param srcInputStream
	 *            输入流
	 * @param toFilePath
	 *            文件地址
	 */
	public static void write(InputStream srcInputStream, String toFilePath) {
		if (srcInputStream == null) {
			throw new IllegalArgumentException("srcInputStream is null");
		}
		if (toFilePath == null) {
			throw new IllegalArgumentException("toFilePath is null");
		}
		File file = new File(toFilePath);
		if (file.exists()) {
			throw new IllegalArgumentException("toFilePath " + toFilePath + " is exists");
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(toFilePath);
			byte[] bytes = new byte[1 * 1024 * 1024];
			int length = 0;
			while ((length = srcInputStream.read(bytes)) > 0) {
				out.write(bytes, 0, length);
			}
			out.flush();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}
			try {
				srcInputStream.close();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * 文件写流<br/>
	 * 
	 * @param srcFilePath
	 *            文件
	 * @param toOutputStream
	 *            输出流
	 */
	public static void read(String srcFilePath, OutputStream toOutputStream) {
		if (srcFilePath == null) {
			throw new IllegalArgumentException("srcFilePath is null");
		}
		File file = new File(srcFilePath);
		if (!file.exists()) {
			throw new IllegalArgumentException("srcFilePath " + srcFilePath + " is not exists");
		}
		if (toOutputStream == null) {
			throw new IllegalArgumentException("toOutputStream is null");
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(srcFilePath);
			byte[] bytes = new byte[1 * 1024 * 1024];
			int length = 0;
			while ((length = in.read(bytes)) > 0) {
				toOutputStream.write(bytes, 0, length);
			}
			toOutputStream.flush();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}
			try {
				toOutputStream.close();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

}
