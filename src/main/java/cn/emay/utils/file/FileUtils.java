package cn.emay.utils.file;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 文件工具
 *
 * @author Frank
 */
public class FileUtils {


    /**
     * 把文件(夹)复制到指定的文件夹中
     *
     * @param srcFilePath 源文件(夹)
     * @param toDirPath   目标文件夹
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
     * @param srcDirPath 源文件夹
     * @param toDirPath  目标文件夹
     */
    public static void copyDir(String srcDirPath, String toDirPath) {
        copy(new File(srcDirPath), new File(toDirPath));
    }

    /**
     * 把文件复制到目标地址
     *
     * @param srcFilePath 源文件
     * @param toFilePath  目标文件
     */
    public static void copyFile(String srcFilePath, String toFilePath) {
        copy(new File(srcFilePath), new File(toFilePath));
    }

    /**
     * 把文件(夹)复制到目标文件(夹)地址
     *
     * @param srcFile 源文件(夹)
     * @param toFile  目标文件(夹)
     */
    public static void copy(File srcFile, File toFile) {
        if (srcFile.isDirectory()) {
            toFile.mkdirs();
            File[] srcFiles = srcFile.listFiles();
            if (srcFiles == null || srcFiles.length == 0) {
                return;
            }
            for (File src : srcFiles) {
                copy(src, new File(toFile, src.getName()));
            }
        } else {
            if (toFile.exists()) {
                throw new IllegalArgumentException("toFile " + toFile.getAbsolutePath() + " is exists");
            }
            toFile.getParentFile().mkdirs();
            try (
                    FileInputStream in = new FileInputStream(srcFile);
                    FileOutputStream out = new FileOutputStream(toFile);
                    FileChannel inChannel = in.getChannel();
                    FileChannel outChannel = out.getChannel()
            ) {
                int maxCount = 64 * 1024 * 1024;
                long size = inChannel.size();
                long position = 0;
                while (position < size) {
                    position += inChannel.transferTo(position, maxCount, outChannel);
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * 把文件(夹)移动到指定的文件夹中
     *
     * @param srcFilePath 源文件(夹)
     * @param toDirPath   目标文件夹
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
     * @param srcDirPath 源文件夹
     * @param toDirPath  目标文件夹
     */
    public static void moveDir(String srcDirPath, String toDirPath) {
        move(new File(srcDirPath), new File(toDirPath));
    }

    /**
     * 把文件移动到目标地址
     *
     * @param srcFilePath 源文件
     * @param toFilePath  目标文件
     */
    public static void moveFile(String srcFilePath, String toFilePath) {
        move(new File(srcFilePath), new File(toFilePath));
    }

    /**
     * 把文件(夹)移动到指定的文件(夹)
     *
     * @param srcFile 源文件(夹)
     * @param toFile  目标文件(夹)
     */
    public static void move(File srcFile, File toFile) {
        if (srcFile.isDirectory()) {
            toFile.mkdirs();
            File[] srcFiles = srcFile.listFiles();
            if (srcFiles != null && srcFiles.length != 0) {
                for (File src : srcFiles) {
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
     * @param filePath 文件(夹)
     */
    public static void delete(String filePath) {
        delete(new File(filePath));
    }

    /**
     * 删除文件(夹)
     *
     * @param file 文件(夹)
     */
    public static void delete(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] srcFiles = file.listFiles();
            if (srcFiles != null && srcFiles.length != 0) {
                for (File src : srcFiles) {
                    delete(src);
                }
            }
        }
        file.delete();
    }

    /**
     * 流写文件<br/>
     *
     * @param srcInputStream 输入流
     * @param toFilePath     文件地址
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
        try (
                FileOutputStream out = new FileOutputStream(toFilePath);
                InputStream srcInputStreamNew = srcInputStream
        ) {
            byte[] bytes = new byte[1024 * 1024];
            int length;
            while ((length = srcInputStreamNew.read(bytes)) > 0) {
                out.write(bytes, 0, length);
            }
            out.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 数据写文件<br/>
     *
     * @param data       数据
     * @param toFilePath 文件地址
     */
    public static void write(String data, String toFilePath) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
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
        try (FileOutputStream out = new FileOutputStream(toFilePath)) {
            out.write(data.getBytes());
            out.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 大量数据逐步写文件<br/>
     *
     * @param data       数据
     * @param toFilePath 文件地址
     */
    public static void write(WriteDataItem data, String toFilePath) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
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
        writeAppend(toFilePath, data);
    }

    /**
     * 文件读流<br/>
     *
     * @param srcFilePath    文件
     * @param toOutputStream 输出流
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
        try (FileInputStream in = new FileInputStream(srcFilePath);
             OutputStream toOutputStreamNew = toOutputStream) {
            byte[] bytes = new byte[1024 * 1024];
            int length;
            while ((length = in.read(bytes)) > 0) {
                toOutputStreamNew.write(bytes, 0, length);
            }
            toOutputStreamNew.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 文件读,并返回所有文本内容<br/>
     *
     * @param srcFilePath 文件
     */
    public static String readText(String srcFilePath) {
        StringBuffer buffer = new StringBuffer();
        readByLine(srcFilePath, line -> {
            buffer.append(line);
            buffer.append("\n");
        });
        return buffer.substring(0, buffer.length() - 1);
    }

    /**
     * 文件逐行读<br/>
     *
     * @param srcFilePath 文件
     * @param lineHandler 行处理器
     */
    public static void readByLine(String srcFilePath, LineHandler lineHandler) {
        if (srcFilePath == null) {
            throw new IllegalArgumentException("srcFilePath is null");
        }
        File file = new File(srcFilePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("srcFilePath " + srcFilePath + " is not exists");
        }
        if (lineHandler == null) {
            throw new IllegalArgumentException("lineHandler is null");
        }
        try (
                FileReader in = new FileReader(srcFilePath);
                BufferedReader br = new BufferedReader(in)
        ) {
            String str;
            while ((str = br.readLine()) != null) {
                lineHandler.handle(str);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 行
     *
     * @author frank
     */
    public interface LineHandler {

        void handle(String line);
    }

    /**
     * 追加写
     *
     * @param filePath 文件路径
     * @param data     数据
     */
    public static void writeAppend(String filePath, String data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (filePath == null) {
            throw new IllegalArgumentException("filePath is null");
        }
        writeAppend(filePath, new WriteDataItem() {

            boolean isWrite = false;

            @Override
            public boolean hasNextData() {
                return !isWrite;
            }

            @Override
            public String nextData() {
                isWrite = true;
                return data;
            }

        });
    }

    /**
     * 追加写
     *
     * @param filePath 文件路径
     * @param data     写片段
     */
    public static void writeAppend(String filePath, WriteDataItem data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (filePath == null) {
            throw new IllegalArgumentException("filePath is null");
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("新建文件失败", e);
            }
        }
        try (FileWriter writer = new FileWriter(filePath, true)) {
            while (data.hasNextData()) {
                String onceData = data.nextData();
                if (onceData != null) {
                    writer.write(onceData);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 写数据item
     *
     * @author frank
     */
    public interface WriteDataItem {

        boolean hasNextData();

        String nextData();
    }


}
