package cn.emay.utils.db;

import cn.emay.utils.db.jdbc.JdbcBeanParser;
import cn.emay.utils.db.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Frank
 */
public class JdbcUtilsTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://100.100.10.91:3306/1804", "1804", "1804");

        List<Map<String, Object>> datas = JdbcUtils.select(conn, "select * from estore_admin", null);

        for (Map<String, Object> data : datas) {
            for (String key : data.keySet()) {
                System.out.print(key + ":" + data.get(key) + ";");
            }
            System.out.println();
        }

        // conn = DriverManager.getConnection("jdbc:mysql://100.100.10.91:3306/1804",
        // "1804", "1804");
        //
        // JDBCUtils.execute(conn, "insert into system_auth_oper (auth_code) values
        // ('123')", null, true);

        conn = DriverManager.getConnection("jdbc:mysql://100.100.10.91:3306/1804", "1804", "1804");

        JdbcUtils.select(conn, "select * from estore_admin", null, (JdbcBeanParser<String>) data -> {
            for (String key : data.keySet()) {
                System.out.print(key + ":" + data.get(key) + ";");
            }
            System.out.println();
            return null;
        });

    }

}
