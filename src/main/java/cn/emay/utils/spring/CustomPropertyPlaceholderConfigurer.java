package cn.emay.utils.spring;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Spring读取的配置，自定义二次处理
 * 
 * @author Frank
 *
 */
public abstract class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		this.handleProps(props);
		super.processProperties(beanFactory, props);
	}

	/**
	 * 处理配置参数
	 * 
	 * @param props
	 */
	protected abstract void handleProps(Properties props);
}