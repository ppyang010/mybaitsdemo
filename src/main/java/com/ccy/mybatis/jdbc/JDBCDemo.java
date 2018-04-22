package com.ccy.mybatis.jdbc;

import com.ccy.mybatis.demo.domain.User;

import java.sql.*;

/**
 * Created by James on 2017/3/26.
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 */
public class JDBCDemo {

    public static final String URL = "jdbc:mysql://192.168.190.200:3306/ccy?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static void main(String[] args) throws SQLException {
//        System.out.println(get(1));
        User user = new User();
        user.setUsername(String.format("hello-%s", Double.valueOf(Math.random()*1000).intValue()));
        System.out.println(insert(user));
    }

    public static int insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, "root", "123456");
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("insert into sys_users(username) VALUES (?)");
//            if (null != user.getId()) {
//                preparedStatement.setInt(1, user.getId());
//            } else {
//                preparedStatement.setNull(1, INTEGER);
//            }
//            preparedStatement.setInt(2, user.getNums());
            preparedStatement.setString(1, user.getUsername());
            int i = preparedStatement.executeUpdate();
            connection.commit();
            return i;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static User get(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection("URL", "root", "123456");
            preparedStatement = connection.prepareStatement("SELECT * FROM test WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
