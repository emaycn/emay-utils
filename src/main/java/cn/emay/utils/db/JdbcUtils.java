package cn.emay.utils.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtils {

	/**
	 * 批量插入，返回id
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Object> saveBatchWithId(Connection connection, String sql, List<Object[]> params) {
		if (connection == null || sql == null || params == null || params.size() == 0) {
			throw new IllegalArgumentException("some params is null");
		}
		return handleTransaction(connection, true, new JdbcExec<List<Object>>() {
			@Override
			public List<Object> exec(Connection connection) {
				List<Object> ids = new ArrayList<Object>();
				PreparedStatement statement = null;
				ResultSet rs = null;
				try {
					statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					int t = 0;
					for (Object[] obj : params) {
						t++;
						for (int i = 0; i < obj.length; i++) {
							Object param = obj[i];
							statement.setObject(i + 1, param);
						}
						statement.addBatch();
						if (t % 1000 == 0) {
							statement.executeBatch();
						}
					}
					statement.executeBatch();
					rs = statement.getGeneratedKeys();
					while (rs.next()) {
						ids.add(rs.getObject(1));
					}
				} catch (SQLException e) {
					throw new IllegalArgumentException(e);
				} finally {
					close(connection, statement, rs);
				}
				return ids;
			}
		});
	}

	/**
	 * 单条插入，返回ID
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Object saveWithId(Connection connection, String sql, Object[] params) {
		if (connection == null || sql == null) {
			throw new IllegalArgumentException("some params is null");
		}
		return handleTransaction(connection, true, new JdbcExec<Object>() {
			@Override
			public Object exec(Connection connection) {
				Object id = null;
				PreparedStatement statement = null;
				ResultSet rs = null;
				try {
					statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							Object param = params[i];
							statement.setObject(i + 1, param);
						}
					}
					statement.executeUpdate();
					rs = statement.getGeneratedKeys();
					while (rs.next()) {
						id = rs.getObject(1);
					}
				} catch (SQLException e) {
					throw new IllegalArgumentException(e);
				} finally {
					close(connection, statement, rs);
				}
				return id;
			}
		});
	}

	/**
	 * 单条更新
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static void upate(Connection connection, String sql, Object[] params) {
		execute(connection, sql, params, true);
	}

	/**
	 * 单条插入
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static void save(Connection connection, String sql, Object[] params) {
		execute(connection, sql, params, true);
	}

	/**
	 * 执行单条
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @param transaction
	 * @return
	 */
	public static void execute(Connection connection, String sql, Object[] params, boolean transaction) {
		if (connection == null || sql == null) {
			throw new IllegalArgumentException("some params is null");
		}
		handleTransaction(connection, transaction, new JdbcExec<Void>() {
			@Override
			public Void exec(Connection connection) {
				PreparedStatement statement = null;
				try {
					statement = connection.prepareStatement(sql);
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							Object param = params[i];
							statement.setObject(i + 1, param);
						}
					}
					statement.executeUpdate();
				} catch (SQLException e) {
					throw new IllegalArgumentException(e);
				} finally {
					close(connection, statement, null);
				}
				return null;
			}
		});
	}

	/**
	 * 批量更新,返回成功的行数
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static void updateBatch(final Connection connection, final String sql, final List<Object[]> params) {
		executeBatch(connection, sql, params, true);
	}

	/**
	 * 批量插入,返回成功的行数
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static void saveBatch(final Connection connection, final String sql, final List<Object[]> params) {
		executeBatch(connection, sql, params, true);
	}

	/**
	 * 执行多条，返回成功的行数
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @param transaction
	 * @return
	 */
	public static void executeBatch(Connection connection, String sql, List<Object[]> params, boolean transaction) {
		if (connection == null || sql == null || params == null || params.size() == 0) {
			throw new IllegalArgumentException("some params is null");
		}
		handleTransaction(connection, transaction, new JdbcExec<Void>() {
			@Override
			public Void exec(Connection connection) {
				PreparedStatement statement = null;
				try {
					statement = connection.prepareStatement(sql);
					int t = 0;
					for (Object[] obj : params) {
						t++;
						for (int i = 0; i < obj.length; i++) {
							Object param = obj[i];
							statement.setObject(i + 1, param);
						}
						statement.addBatch();
						if (t % 1000 == 0) {
							statement.executeBatch();
						}
					}
					statement.executeBatch();
				} catch (SQLException e) {
					throw new IllegalArgumentException(e);
				} finally {
					close(connection, statement, null);
				}
				return null;
			}
		});
	}

	/**
	 * 执行多条，返回成功的行数
	 * 
	 * @param connection
	 * @param sqls
	 * @param transaction
	 * @return
	 */
	public static void executeBatch(Connection connection, boolean transaction, String... sqls) {
		if (connection == null || sqls == null || sqls.length == 0) {
			throw new IllegalArgumentException("some params is null");
		}
		handleTransaction(connection, transaction, new JdbcExec<Void>() {
			@Override
			public Void exec(Connection connection) {
				Statement statement = null;
				try {
					statement = connection.createStatement();
					int t = 0;
					for (String sql : sqls) {
						t++;
						statement.addBatch(sql);
						if (t % 1000 == 0) {
							statement.executeBatch();
						}
					}
					statement.executeBatch();
				} catch (SQLException e) {
					throw new IllegalArgumentException(e);
				} finally {
					close(connection, statement, null);
				}
				return null;
			}
		});
	}

	/**
	 * 查询，每一行用map表示，返回多行
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> select(Connection connection, String sql, Object[] params) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					Object param = params[i];
					statement.setObject(i + 1, param);
				}
			}
			rs = statement.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int length = meta.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= length; i++) {
					map.put(meta.getColumnName(i), rs.getObject(i));
				}
				result.add(map);
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} finally {
			close(connection, statement, rs);
		}
		return result;
	}

	/**
	 * 查询单一数据，map表示
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String, Object> selectUnique(Connection connection, String sql, Object[] params) {
		Map<String, Object> map = new HashMap<String, Object>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					Object param = params[i];
					statement.setObject(i + 1, param);
				}
			}
			rs = statement.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int length = meta.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= length; i++) {
					map.put(meta.getColumnName(i), rs.getObject(i));
				}
				break;
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} finally {
			close(connection, statement, rs);
		}
		return map;
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
	public static <T> T selectUnique(Connection connection, String sql, Object[] params, JdbcBeanParser<T> bean) {
		Map<String, Object> map = selectUnique(connection, sql, params);
		return bean.parser(map);
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
	public static <T> List<T> select(Connection connection, String sql, Object[] params, JdbcBeanParser<T> bean) {
		List<Map<String, Object>> list = select(connection, sql, params);
		List<T> beans = new ArrayList<T>();
		for (Map<String, Object> data : list) {
			beans.add(bean.parser(data));
		}
		return beans;
	}

	/**
	 * 处理事务
	 * 
	 * @param connection
	 * @param openTransaction
	 * @param exec
	 * @return
	 */
	public static <T> T handleTransaction(Connection connection, boolean openTransaction, JdbcExec<T> exec) {
		T t = null;
		try {
			boolean isOldOpen = connection.getAutoCommit();
			if (openTransaction && isOldOpen) {
				connection.setAutoCommit(false);
			}
			t = exec.exec(connection);
			if (openTransaction) {
				connection.commit();
			}
			connection.setAutoCommit(isOldOpen);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
		return t;
	}

	/**
	 * 回收或者关闭连接
	 * 
	 * @param connection
	 * @param statement
	 * @param rs
	 */
	public static void close(Connection connection, Statement statement, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}

	}

}
