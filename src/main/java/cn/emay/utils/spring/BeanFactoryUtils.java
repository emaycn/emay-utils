package cn.emay.utils.spring;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring bean util <br/>
 * 
 * @author shidongxu
 *
 */
@Component
public class BeanFactoryUtils implements ApplicationContextAware {

	/**
	 * spring容器
	 */
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		setContext(applicationContext);
	}

	/**
	 * 通过名字获取Bean
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	/**
	 * 通过class类型获取bean
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		Map<?, T> beanMap = context.getBeansOfType(clazz);
		if (beanMap == null || beanMap.size() == 0) {
			return null;
		}
		return beanMap.values().iterator().next();
	}

	/**
	 * 获取容器
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}

	private static void setContext(ApplicationContext context) {
		BeanFactoryUtils.context = context;
	}
}
