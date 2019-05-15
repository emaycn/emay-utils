package cn.emay.utils.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应工具
 * 
 * @author Frank
 *
 */
public class ResponseUtils {

	/**
	 * 加入Cookie
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		addCookie(response, name, value, null, null, null, 0, maxAge, false);
	}

	/**
	 * 加入Cookie
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, String comment, String domain, String path, int version, int maxAge, boolean secure) {
		Cookie cookie = new Cookie(name, value);
		cookie.setComment(comment);
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setSecure(secure);
		cookie.setVersion(version);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 删除Cookie
	 */
	public static void deleteCookie(HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

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
				if (out != null) {
					out.close();
				}
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
