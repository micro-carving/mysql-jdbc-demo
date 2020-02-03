package olin.util;

import java.sql.*;

/**
 * Jdbc连接工具类
 */
public class JdbcUtil {
    private static Connection connection = null;
//    private static final String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false";
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String className = "com.mysql.cj.jdbc.Driver";

    public JdbcUtil() {}

    /**
     * 使用JDBC连接MySql数据库
     * @return Connection对象
     */
    public static Connection getConnection() {
        try {
            // 通过反射加载驱动
            Class.forName(className);
            // 通过驱动管理器得到连接
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 数据库相关对象的释放
     * @param statement：执行静态SQL语句并返回的结果对象
     * @param connection：与特定数据库的连接对象
     */
    public static void release(Statement statement, Connection connection){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 数据库相关对象的释放，方法重载
     * @param resultSet：数据集对象
     */
    public static void release(ResultSet resultSet, Statement statement, Connection connection){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        release(statement,connection);
    }
}
