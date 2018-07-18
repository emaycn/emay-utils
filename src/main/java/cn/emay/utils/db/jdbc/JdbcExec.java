package cn.emay.utils.db.jdbc;

import java.sql.Connection;

/**
 * JDBC执行器
 * 
 * @author Frank
 *
 * @param <T>
 */
public interface JdbcExec<T> {

	/**
	 * 执行
	 * 
	 * @param connection
	 * @return
	 */
	public T exec(Connection connection);

	/**
	 * 关闭
	 */
	public void close();

}
