package cn.emay.utils.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP 压缩工具
 *
 * @author Frank
 */
public class GzipUtils {

    /**
     * 数据压缩
     *
     * @param bytes 数据
     */
    public static byte[] compress(byte[] bytes) {
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                GZIPOutputStream gos = new GZIPOutputStream(out)
        ) {
            gos.write(bytes);
            gos.finish();
            gos.flush();
            return out.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 数据解压
     *
     * @param bytes 数据
     */
    public static byte[] decompress(byte[] bytes) {
        try (
                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                GZIPInputStream gin = new GZIPInputStream(in);
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            int count;
            byte[] data = new byte[1024];
            while ((count = gin.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);
            }
            out.flush();
            return out.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
