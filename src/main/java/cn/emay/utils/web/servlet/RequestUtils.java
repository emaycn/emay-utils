package cn.emay.utils.web.servlet;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.emay.utils.date.DateUtils;

/**
 * 请求工具
 * 
 * @author Frank
 *
 */
public class RequestUtils {

	public final static String X_REQUESTED_WITH = "X-Requested-With";
	public final static String XMLHTTPREQUEST = "XMLHttpRequest";

	/**
	 * 获取请求参数
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String name) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals("")) {
			temp = null;
		} else {
			temp = temp.trim();
		}
		return temp;
	}

	/**
	 * 获取String类型的请求参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String name, String defaultValue) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals("")) {
			temp = defaultValue;
		} else {
			temp = temp.trim();
		}
		return temp;
	}

	/**
	 * 获取Long类型的请求参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static long getLongParameter(HttpServletRequest request, String name, long defaultNum) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals("")) {
			return defaultNum;
		}
		long num = defaultNum;
		try {
			num = Long.valueOf(temp.trim());
		} catch (Exception ignored) {
		}
		return num;
	}

	/**
	 * 获取Boolean类型的请求参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean defaultValue) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals("")) {
			return defaultValue;
		}
		if (temp.equalsIgnoreCase("true") || temp.equalsIgnoreCase("on") || temp.equalsIgnoreCase("1")) {
			return true;
		}
		if (temp.equalsIgnoreCase("false") || temp.equalsIgnoreCase("off") || temp.equalsIgnoreCase("0")) {
			return false;
		}
		return defaultValue;
	}

	/**
	 * 获取int类型的请求参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static int getIntParameter(HttpServletRequest request, String name, int defaultNum) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals("")) {
			return defaultNum;
		}
		int num = defaultNum;
		try {
			num = Integer.valueOf(temp);
		} catch (Exception e) {
		}
		return num;
	}

	/**
	 * 获取float类型的请求参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static float getFloatParameter(HttpServletRequest request, String name, float defaultNum) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals("")) {
			return defaultNum;
		}
		float num = defaultNum;
		try {
			num = Float.valueOf(temp);
		} catch (Exception e) {
		}
		return num;
	}

	/**
	 * 获取double类型的请求参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static double getDoubleParameter(HttpServletRequest request, String name, double defaultNum) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals("")) {
			return defaultNum;
		}
		double num = defaultNum;
		try {
			num = Double.valueOf(temp);
		} catch (Exception e) {
		}
		return num;
	}

	/**
	 * 获取date类型的请求参数
	 * 
	 * @param request
	 * @param name
	 * @param format
	 * @param defaultValue
	 * @return
	 */
	public static Date getDateParameter(HttpServletRequest request, String name, String format, Date defaultValue) {
		String temp = request.getParameter(name);
		Date date = DateUtils.parseDate(temp, format);
		if (date == null) {
			date = defaultValue;
		}
		return date;
	}

	/**
	 * 获取BigDecimal类型的请求参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static BigDecimal getBigDecimalParameter(HttpServletRequest request, String name, BigDecimal defaultValue) {
		String temp = request.getParameter(name);
		BigDecimal value = defaultValue;
		try {
			value = new BigDecimal(temp);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 获取请求数据
	 * 
	 * @param request
	 * @return
	 */
	public static byte[] getrequestBytesData(HttpServletRequest request) {
		DataInputStream in = null;
		byte[] data = null;
		try {
			int totalbytes = request.getContentLength();
			if (totalbytes <= 0) {
				return null;
			}
			data = new byte[totalbytes];
			in = new DataInputStream(request.getInputStream());
			in.readFully(data);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					throw new IllegalArgumentException(e1);
				}
			}
		}
		return data;
	}

	/**
	 * 获取真实IP<br/>
	 * 按照X-Forwarded-For、X-Real-IP、getRemoteAddr的顺序找
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteRealIp(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");
		String ip = null;
		if (forwarded == null || forwarded.length() == 0 || "unknown".equalsIgnoreCase(forwarded)) {
			if (realIp == null || realIp.length() == 0 || "unknown".equalsIgnoreCase(realIp)) {
				ip = remoteAddr;
			} else {
				ip = realIp;
			}
		} else {
			ip = forwarded.split(",")[0];
		}
		return ip;
	}

	/**
	 * 查询Cookie
	 */
	public static Cookie findCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 是否AJAX请求
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return XMLHTTPREQUEST.equalsIgnoreCase(request.getHeader(X_REQUESTED_WITH));
	}

	/**
	 * 获取 当前 域名
	 *
	 * @param request
	 * @return
	 */
	public static String getLocalAddress(HttpServletRequest request) {
		String uri = request.getRequestURI() == null ? "" : request.getRequestURI();
		String url = request.getRequestURL() == null ? "" : request.getRequestURL().toString();
		String contextPath = request.getContextPath() == null ? "" : request.getContextPath();
		return url.replace(uri, "") + contextPath;
	}

}
