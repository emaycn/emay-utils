package cn.emay.utils.db.jdbc;

import java.sql.Connection;

/**
 * JDBC执行器
 *
 * @param <T>
 * @author Frank
 */
public interface JdbcExec<T> {

    /**
     * 执行
     */
    T exec(Connection connection);

    /**
     * 关闭
     */
    void close();

}
