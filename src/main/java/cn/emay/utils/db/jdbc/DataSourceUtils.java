package cn.emay.utils.db.jdbc;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 数据库连接池工具
 *
 * @author Frank
 */
public class DataSourceUtils {

    /**
     * 批量插入，返回id
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     * @return id
     */
    public static List<Object> saveBatchWithId(DataSource dataSource, String sql, List<Object[]> params) {
        try {
            return JdbcUtils.saveBatchWithId(dataSource.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 单条插入，返回ID
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     * @return id
     */
    public static Object saveWithId(DataSource dataSource, String sql, Object[] params) {
        try {
            return JdbcUtils.saveWithId(dataSource.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 单条更新
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     */
    public static void update(DataSource dataSource, String sql, Object[] params) {
        try {
            JdbcUtils.update(dataSource.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 单条插入
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     */
    public static void save(DataSource dataSource, String sql, Object[] params) {
        try {
            JdbcUtils.save(dataSource.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 执行单条
     *
     * @param dataSource  数据库连接池
     * @param sql         sql
     * @param params      参数
     * @param transaction 是否开启事务
     */
    public static void execute(DataSource dataSource, String sql, Object[] params, boolean transaction) {
        try {
            JdbcUtils.execute(dataSource.getConnection(), sql, params, transaction);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 批量更新,返回成功的行数
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     */
    public static void updateBatch(final DataSource dataSource, final String sql, final List<Object[]> params) {
        try {
            JdbcUtils.updateBatch(dataSource.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 批量插入,返回成功的行数
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     */
    public static void saveBatch(final DataSource dataSource, final String sql, final List<Object[]> params) {
        try {
            JdbcUtils.saveBatch(dataSource.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 执行多条，返回成功的行数
     *
     * @param dataSource  数据库连接池
     * @param sql         sql
     * @param params      参数
     * @param transaction 是否开启事务
     */
    public static void executeBatch(DataSource dataSource, String sql, List<Object[]> params, boolean transaction) {
        try {
            JdbcUtils.executeBatch(dataSource.getConnection(), sql, params, transaction);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 执行多条，返回成功的行数
     *
     * @param dataSource  数据库连接池
     * @param sql         sql
     * @param transaction 是否开启事务
     */
    public static void executeBatch(DataSource dataSource, boolean transaction, String... sql) {
        try {
            JdbcUtils.executeBatch(dataSource.getConnection(), transaction, sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 查询，每一行用map表示，返回多行
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     * @return 数据
     */
    public static List<Map<String, Object>> select(DataSource dataSource, String sql, Object[] params) {
        try {
            return JdbcUtils.select(dataSource.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 查询单一数据，map表示
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     * @return 数据
     */
    public static Map<String, Object> selectUnique(DataSource dataSource, String sql, Object[] params) {
        try {
            return JdbcUtils.selectUnique(dataSource.getConnection(), sql, params);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 查询单一数据，传入jdbcBean自定义转换类型
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     * @param bean       数据解析器
     * @return 数据
     */
    public static <T> T selectUnique(DataSource dataSource, String sql, Object[] params, JdbcBeanParser<T> bean) {
        try {
            return JdbcUtils.selectUnique(dataSource.getConnection(), sql, params, bean);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 查询批量数据，传入jdbcBean自定义转换类型
     *
     * @param dataSource 数据库连接池
     * @param sql        sql
     * @param params     参数
     * @param bean       数据解析器
     * @return 数据
     */
    public static <T> List<T> select(DataSource dataSource, String sql, Object[] params, JdbcBeanParser<T> bean) {
        try {
            return JdbcUtils.select(dataSource.getConnection(), sql, params, bean);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
