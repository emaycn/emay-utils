package cn.emay.utils.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

/**
 * 响应工具
 * 
 * @author Frank
 *
 */
public class ResponseUtils {

	/**
	 * 输出字节流
	 * 
	 * @param response
	 * @param bytes
	 */
	public static void outputBytes(HttpServletResponse response, byte[] bytes) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 以Jsonp方式输出
	 * 
	 * @param response
	 * @param responseStr
	 * @param jsonp
	 *            jsonp标识
	 */
	public static void outputWithJsonp(HttpServletResponse response, String responseStr, String jsonp) {
		response.setContentType("text/javascript;charset=utf-8");
		OutputStream out = null;
		try {
			responseStr = jsonp + "(eval(" + responseStr + "))";
			out = response.getOutputStream();
			out.write(responseStr.getBytes("UTF-8"));
			out.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
