package cn.emay.utils.srping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * 自定义web异常处理器<br/>
 * 注册才能使用
 * 
 * @author Frank
 *
 */
public class CustomHandlerExceptionResolver extends DefaultHandlerExceptionResolver {

	private final Logger log = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex != null) {
			log.error(request.getRequestURI(), ex);
		}
		return super.doResolveException(request, response, handler, ex);
	}

}
