package cn.emay.utils.db;

import java.util.Map;

public interface JdbcBean<T extends Object> {
	
	public T replaceTo(Map<String,Object> data);

}
