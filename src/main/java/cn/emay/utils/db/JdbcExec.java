package cn.emay.utils.db;

import java.sql.Connection;

public interface JdbcExec<T> {

	public T exec(Connection connection);

	public void close();

}
