package cn.emay.utils.db.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * 数据库连接池工具
 * 
 * @author Frank
 *
 */
public class DataSourceUtils {

	/**
	 * 批量插入，返回id
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Object> saveBatchWithId(DataSource dataSource, String sql, List<Object[]> params) {
		try {
			return JDBCUtils.saveBatchWithId(dataSource.getConnection(), sql, params);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 单条插入，返回ID
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Object saveWithId(DataSource dataSource, String sql, Object[] params) {
		try {
			return JDBCUtils.saveWithId(dataSource.getConnection(), sql, params);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 单条更新
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static void upate(DataSource dataSource, String sql, Object[] params) {
		try {
			JDBCUtils.upate(dataSource.getConnection(), sql, params);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 单条插入
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static void save(DataSource dataSource, String sql, Object[] params) {
		try {
			JDBCUtils.save(dataSource.getConnection(), sql, params);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 执行单条
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @param transaction
	 * @return
	 */
	public static void execute(DataSource dataSource, String sql, Object[] params, boolean transaction) {
		try {
			JDBCUtils.execute(dataSource.getConnection(), sql, params, transaction);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 批量更新,返回成功的行数
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static void updateBatch(final DataSource dataSource, final String sql, final List<Object[]> params) {
		try {
			JDBCUtils.updateBatch(dataSource.getConnection(), sql, params);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 批量插入,返回成功的行数
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static void saveBatch(final DataSource dataSource, final String sql, final List<Object[]> params) {
		try {
			JDBCUtils.saveBatch(dataSource.getConnection(), sql, params);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 执行多条，返回成功的行数
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @param transaction
	 * @return
	 */
	public static void executeBatch(DataSource dataSource, String sql, List<Object[]> params, boolean transaction) {
		try {
			JDBCUtils.executeBatch(dataSource.getConnection(), sql, params, transaction);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 执行多条，返回成功的行数
	 * 
	 * @param dataSource
	 * @param sqls
	 * @param transaction
	 * @return
	 */
	public static void executeBatch(DataSource dataSource, boolean transaction, String... sqls) {
		try {
			JDBCUtils.executeBatch(dataSource.getConnection(), transaction, sqls);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 查询，每一行用map表示，返回多行
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> select(DataSource dataSource, String sql, Object[] params) {
		try {
			return JDBCUtils.select(dataSource.getConnection(), sql, params);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 查询单一数据，map表示
	 * 
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String, Object> selectUnique(DataSource dataSource, String sql, Object[] params) {
		try {
			return JDBCUtils.selectUnique(dataSource.getConnection(), sql, params);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 查询单一数据，传入jdbcbean自定义转换类型
	 * 
	 * @param datasource
	 * @param sql
	 * @param params
	 * @param bean
	 * @return
	 */
	public static <T> T selectUnique(DataSource dataSource, String sql, Object[] params, JdbcBeanParser<T> bean) {
		try {
			return JDBCUtils.selectUnique(dataSource.getConnection(), sql, params, bean);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 查询批量数据，传入jdbcbean自定义转换类型
	 * 
	 * @param datasource
	 * @param sql
	 * @param params
	 * @param bean
	 * @return
	 */
	public static <T> List<T> select(DataSource dataSource, String sql, Object[] params, JdbcBeanParser<T> bean) {
		try {
			return JDBCUtils.select(dataSource.getConnection(), sql, params, bean);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
