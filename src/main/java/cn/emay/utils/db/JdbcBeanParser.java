package cn.emay.utils.db;

import java.util.Map;

public interface JdbcBeanParser<T extends Object> {

	public T parser(Map<String, Object> data);

}
