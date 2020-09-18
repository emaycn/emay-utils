package cn.emay.utils.compress;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * zip工具
 *
 * @author Frank
 */
public class ZipUtils {


    /**
     * 压缩
     *
     * @param srcPath       待压缩文件/文件夹路径
     * @param targetZipPath 压缩文件路径
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
        try (FileOutputStream cos = new FileOutputStream(targetZip)) {
            zip(srcPath, cos);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 压缩<br/>
     *
     * @param srcPath            待压缩文件/文件夹路径
     * @param targetOutputStream 压缩流
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
        try (ZipOutputStream zos = new ZipOutputStream(new CheckedOutputStream(targetOutputStream, new CRC32()))) {
            zipEntry(srcFile.getParentFile().getAbsolutePath(), srcFile, zos);
            zos.flush();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 压缩逻辑
     *
     * @param rootPath 跟路径
     * @param srcFile  源文件
     * @param zos      输出流
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
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile))) {
                ZipEntry entry = new ZipEntry(srcFile.getAbsolutePath().substring(rootPath.length()));
                zos.putNextEntry(entry);
                int size;
                byte[] data = new byte[1024];
                while ((size = bis.read(data, 0, data.length)) != -1) {
                    zos.write(data, 0, size);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * 解压缩
     *
     * @param srcZipFilePath 待解压zip文件路径
     * @param targetDirPath  解压文件夹路径
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
        try (ZipFile zip = new ZipFile(srcZip)) {
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
                    try (
                            BufferedInputStream bis = new BufferedInputStream(zip.getInputStream(entry));
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(entryFile))
                    ) {
                        int size;
                        byte[] data = new byte[1024];
                        while ((size = bis.read(data, 0, data.length)) != -1) {
                            bos.write(data, 0, size);
                        }
                        bos.flush();
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 压缩<br/>
     *
     * @param srcInputStream     输入流
     * @param targetOutputStream 输出流
     */
    public static void zip(InputStream srcInputStream, OutputStream targetOutputStream) {
        if (srcInputStream == null) {
            throw new IllegalArgumentException("srcInputStream is null");
        }
        if (targetOutputStream == null) {
            throw new IllegalArgumentException("targetOutputStream is null");
        }
        try (
                InputStream srcInputStreamNew = srcInputStream;
                OutputStream targetOutputStreamNew = targetOutputStream;
                ZipOutputStream zos = new ZipOutputStream(new CheckedOutputStream(targetOutputStreamNew, new CRC32()))
        ) {
            ZipEntry entry = new ZipEntry("0");
            zos.putNextEntry(entry);
            int size;
            byte[] data = new byte[1024];
            while ((size = srcInputStreamNew.read(data, 0, data.length)) != -1) {
                zos.write(data, 0, size);
            }
            zos.flush();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 解压缩<br/>
     *
     * @param srcInputStream     输入流
     * @param targetOutputStream 输出流
     */
    public static void unzip(InputStream srcInputStream, OutputStream targetOutputStream) {
        if (srcInputStream == null) {
            throw new IllegalArgumentException("srcInputStream is null");
        }
        if (targetOutputStream == null) {
            throw new IllegalArgumentException("targetOutputStream is null");
        }
        try (
                InputStream srcInputStreamNew = srcInputStream;
                OutputStream targetOutputStreamNew = targetOutputStream;
                ZipInputStream zin = new ZipInputStream(srcInputStreamNew)
        ) {
            zin.getNextEntry();
            int size;
            byte[] data = new byte[1024];
            while ((size = zin.read(data, 0, data.length)) != -1) {
                targetOutputStreamNew.write(data, 0, size);
            }
            targetOutputStreamNew.flush();
            zin.closeEntry();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}