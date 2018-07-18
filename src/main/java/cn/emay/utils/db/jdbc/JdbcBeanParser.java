package cn.emay.utils.db.jdbc;

import java.util.Map;

/**
 * 数据转换
 * 
 * @author Frank
 *
 * @param <T>
 */
public interface JdbcBeanParser<T extends Object> {

	/**
	 * 转换
	 * 
	 * @param data
	 * @return
	 */
	public T parser(Map<String, Object> data);

}
