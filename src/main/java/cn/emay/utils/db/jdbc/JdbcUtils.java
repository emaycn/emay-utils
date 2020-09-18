package cn.emay.utils.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jdbc工具类
 *
 * @author Frank
 */
public class JdbcUtils {

    /**
     * 批量插入，返回id
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     * @return id
     */
    public static List<Object> saveBatchWithId(Connection connection, String sql, List<Object[]> params) {
        if (connection == null || sql == null || params == null || params.size() == 0) {
            throw new IllegalArgumentException("some params is null");
        }
        return handleTransaction(connection, true, new JdbcExec<List<Object>>() {

            PreparedStatement statement = null;
            ResultSet rs = null;

            @Override
            public List<Object> exec(Connection connection) {
                List<Object> ids = new ArrayList<>();
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
                }
                return ids;
            }

            @Override
            public void close() {
                JdbcUtils.close(connection, statement, rs);
            }
        });
    }

    /**
     * 单条插入，返回ID
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     * @return id
     */
    public static Object saveWithId(Connection connection, String sql, Object[] params) {
        if (connection == null || sql == null) {
            throw new IllegalArgumentException("some params is null");
        }
        return handleTransaction(connection, true, new JdbcExec<Object>() {

            PreparedStatement statement = null;
            ResultSet rs = null;

            @Override
            public Object exec(Connection connection) {
                Object id = null;
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
                }
                return id;
            }

            @Override
            public void close() {
                JdbcUtils.close(connection, statement, rs);
            }
        });
    }

    /**
     * 单条更新
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     */
    public static void update(Connection connection, String sql, Object[] params) {
        execute(connection, sql, params, true);
    }

    /**
     * 单条插入
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     */
    public static void save(Connection connection, String sql, Object[] params) {
        execute(connection, sql, params, true);
    }

    /**
     * 执行单条
     *
     * @param connection  链接
     * @param sql         sql
     * @param params      参数
     * @param transaction 是否开启事务
     */
    public static void execute(Connection connection, String sql, Object[] params, boolean transaction) {
        if (connection == null || sql == null) {
            throw new IllegalArgumentException("some params is null");
        }
        handleTransaction(connection, transaction, new JdbcExec<Void>() {

            PreparedStatement statement = null;

            @Override
            public Void exec(Connection connection) {

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
                }
                return null;
            }

            @Override
            public void close() {
                JdbcUtils.close(connection, statement, null);
            }
        });
    }

    /**
     * 批量更新,返回成功的行数
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     */
    public static void updateBatch(final Connection connection, final String sql, final List<Object[]> params) {
        executeBatch(connection, sql, params, true);
    }

    /**
     * 批量插入,返回成功的行数
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     */
    public static void saveBatch(final Connection connection, final String sql, final List<Object[]> params) {
        executeBatch(connection, sql, params, true);
    }

    /**
     * 执行多条，返回成功的行数
     *
     * @param connection  链接
     * @param sql         sql
     * @param params      参数
     * @param transaction 是否开启事务
     */
    public static void executeBatch(Connection connection, String sql, List<Object[]> params, boolean transaction) {
        if (connection == null || sql == null || params == null || params.size() == 0) {
            throw new IllegalArgumentException("some params is null");
        }
        handleTransaction(connection, transaction, new JdbcExec<Void>() {

            PreparedStatement statement = null;

            @Override
            public Void exec(Connection connection) {
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
                }
                return null;
            }

            @Override
            public void close() {
                JdbcUtils.close(connection, statement, null);
            }

        });
    }

    /**
     * 执行多条
     *
     * @param connection  链接
     * @param sql         sql
     * @param transaction 是否开启事务
     */
    public static void executeBatch(Connection connection, boolean transaction, String... sql) {
        if (connection == null || sql == null || sql.length == 0) {
            throw new IllegalArgumentException("some params is null");
        }
        handleTransaction(connection, transaction, new JdbcExec<Void>() {

            Statement statement = null;

            @Override
            public Void exec(Connection connection) {
                try {
                    statement = connection.createStatement();
                    int t = 0;
                    for (String sql : sql) {
                        t++;
                        statement.addBatch(sql);
                        if (t % 1000 == 0) {
                            statement.executeBatch();
                        }
                    }
                    statement.executeBatch();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
                return null;
            }

            @Override
            public void close() {
                JdbcUtils.close(connection, statement, null);
            }
        });
    }

    /**
     * 查询，每一行用map表示，返回多行
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     * @return 数据
     */
    public static List<Map<String, Object>> select(Connection connection, String sql, Object[] params) {
        List<Map<String, Object>> result = new ArrayList<>();
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
                Map<String, Object> map = new HashMap<>(length);
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
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     * @return 数据
     */
    public static Map<String, Object> selectUnique(Connection connection, String sql, Object[] params) {
        Map<String, Object> map = new HashMap<>(32);
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
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            close(connection, statement, rs);
        }
        return map;
    }

    /**
     * 查询单一数据，传入jdbcBean自定义转换类型
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     * @param bean       数据解析器
     * @return 数据
     */
    public static <T> T selectUnique(Connection connection, String sql, Object[] params, JdbcBeanParser<T> bean) {
        Map<String, Object> map = selectUnique(connection, sql, params);
        return bean.parser(map);
    }

    /**
     * 查询批量数据，传入jdbcBean自定义转换类型
     *
     * @param connection 链接
     * @param sql        sql
     * @param params     参数
     * @param bean       数据解析器
     * @return 数据
     */
    public static <T> List<T> select(Connection connection, String sql, Object[] params, JdbcBeanParser<T> bean) {
        List<Map<String, Object>> list = select(connection, sql, params);
        List<T> beans = new ArrayList<>();
        for (Map<String, Object> data : list) {
            beans.add(bean.parser(data));
        }
        return beans;
    }

    /**
     * 处理事务
     *
     * @param connection      链接
     * @param openTransaction 是否开启事务
     * @param exec            执行器
     * @return 结果
     */
    public static <T> T handleTransaction(Connection connection, boolean openTransaction, JdbcExec<T> exec) {
        T t;
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
        } finally {
            exec.close();
        }
        return t;
    }

    /**
     * 回收或者关闭连接
     *
     * @param connection 链接
     * @param statement  陈述
     * @param rs         结果集
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
