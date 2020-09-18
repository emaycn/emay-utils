package cn.emay.utils.db.jdbc;

import java.util.Map;

/**
 * 数据转换
 *
 * @param <T>
 * @author Frank
 */
public interface JdbcBeanParser<T> {

    /**
     * 转换
     *
     * @param data map数据
     * @return 实体数据
     */
    T parser(Map<String, Object> data);

}
