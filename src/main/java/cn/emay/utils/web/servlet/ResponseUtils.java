package cn.emay.utils.web.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 响应工具<br/>
 * 不再推荐使用，请使用 cn.emay.utils.web.spring.HttpResponseUtils
 *
 * @author Frank
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
        if (domain != null) {
            cookie.setDomain(domain);
        }
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
     */
    public static void outputBytes(HttpServletResponse response, byte[] bytes) {
        try (OutputStream out = response.getOutputStream()) {
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 以Jsonp方式输出
     */
    public static void outputWithJsonp(HttpServletResponse response, String responseStr, String callback) {
        response.setContentType("text/javascript;charset=utf-8");
        try (OutputStream out = response.getOutputStream()) {
            responseStr = callback + "(eval(" + responseStr + "))";
            out.write(responseStr.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
