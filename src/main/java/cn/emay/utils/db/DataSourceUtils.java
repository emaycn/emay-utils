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

import javax.sql.DataSource;

public class DataSourceUtils {

	/**
	 * 批量插入，返回所有插入的数据的id
	 * 
	 * @param datasource
	 * @param sql
	 * @param params
	 * @param transaction
	 * @return
	 */
	public static List<Object> saveBatch(DataSource datasource, String sql, List<Object[]> params, boolean transaction) {
		Connection connection = null;
		List<Object> ids = new ArrayList<Object>();
		if (params == null || params.size() == 0) {
			return ids;
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = datasource.getConnection();
			if (transaction) {
				connection.setAutoCommit(false);
			}
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
					statement.executeUpdate();
				}
			}
			statement.executeUpdate();
			if (transaction) {
				connection.commit();
			}
			rs = statement.getGeneratedKeys();
			while (rs.next()) {
				ids.add(rs.getObject(1));
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				if (transaction) {
					connection.setAutoCommit(true);
				}
				JdbcUtils.close(connection, statement, rs);
			} catch (SQLException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return ids;
	}

	/**
	 * 单条插入，返回ID
	 * 
	 * @param datasource
	 * @param sql
	 * @param params
	 * @param transaction
	 * @return
	 */
	public static Object save(DataSource datasource, String sql, Object[] params, boolean transaction) {
		Object id = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = datasource.getConnection();
			if (transaction) {
				connection.setAutoCommit(false);
			}
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					Object param = params[i];
					statement.setObject(i + 1, param);
				}
			}
			statement.executeUpdate();
			if (transaction) {
				connection.commit();
			}
			rs = statement.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getObject(1);
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				if (transaction) {
					connection.setAutoCommit(true);
				}
				JdbcUtils.close(connection, statement, rs);
			} catch (SQLException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return id;
	}

	/**
	 * 执行，返回影响行数
	 * 
	 * @param datasource
	 * @param sql
	 * @param params
	 * @param transaction
	 * @return
	 */
	public static int execute(DataSource datasource, String sql, Object[] params, boolean transaction) {
		Connection connection = null;
		int number = 0;
		PreparedStatement statement = null;
		try {
			connection = datasource.getConnection();
			if (transaction) {
				connection.setAutoCommit(false);
			}
			statement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					Object param = params[i];
					statement.setObject(i + 1, param);
				}
			}
			number = statement.executeUpdate();
			if (transaction) {
				connection.commit();
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				if (transaction) {
					connection.setAutoCommit(true);
				}
				JdbcUtils.close(connection, statement, null);
			} catch (SQLException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return number;
	}

	/**
	 * 查询，每一行用map表示，返回多行
	 * 
	 * @param datasource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> select(DataSource datasource, String sql, Object[] params) {
		Connection connection = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = datasource.getConnection();
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
			try {
				JdbcUtils.close(connection, statement, rs);
			} catch (SQLException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return result;
	}

	/**
	 * 查询单一数据，map表示
	 * 
	 * @param datasource
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String, Object> selectUnique(DataSource datasource, String sql, Object[] params) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = datasource.getConnection();
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
				continue;
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				JdbcUtils.close(connection, statement, rs);
			} catch (SQLException e) {
				throw new IllegalArgumentException(e);
			}
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
	public static <T> T selectUnique(DataSource datasource, String sql, Object[] params, JdbcBean<T> bean) {
		Map<String, Object> map = selectUnique(datasource, sql, params);
		return bean.replaceTo(map);
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
	public static <T> List<T> select(DataSource datasource, String sql, Object[] params, JdbcBean<T> bean) {
		List<Map<String, Object>> list = select(datasource, sql, params);
		List<T> beans = new ArrayList<T>();
		for (Map<String, Object> data : list) {
			beans.add(bean.replaceTo(data));
		}
		return beans;
	}

}
