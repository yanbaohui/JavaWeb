package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class JdbcUtils {

    private  static DruidDataSource dataSource;
    private  static ThreadLocal<Connection> coons = new ThreadLocal();


    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties 属性配置文
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回 null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection conn = coons.get();

        if(conn == null){
            try {
                conn = dataSource.getConnection();
                coons.set(conn); //保存conn对象到ThreadLocal中
                conn.setAutoCommit(false); //设置手动提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn;

    }


    public static void commitAndClose(){
        Connection connection = coons.get();
        if(connection != null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则会出错。因为tomcat服务器使用了线程池
        coons.remove();

    }

    public static void rollbackAndClose(){
        Connection connection = coons.get();
        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则会出错。因为tomcat服务器使用了线程池
        coons.remove();


    }

//    /**
//     * 关闭连接，放回数据库连接池
//     * @param conn
//     */
//    public static void close(Connection conn){
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
