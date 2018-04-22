package com.ccy.mybatis.v1;

import com.ccy.mybatis.demo.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTool {




    public static final String URL = "jdbc:mysql://192.168.190.200:3306/ccy?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static void main(String[] args) throws SQLException {
        System.out.println(execute("select * from sys_users where username = ?",new String[]{"admin"}));
//        User user = new User();
//        user.setUsername(String.format("hello-%s", Double.valueOf(Math.random()*1000).intValue()));
//        System.out.println(insert(user));
    }


    public static <T> List execute(String sql, Object [] args){
        try {

            //在一个应用中 123 567这些步骤是重复的
            //1、获取连接
            Connection connection = DriverManager.getConnection(URL, "root", "123456");

            //2、创建语句集
            //预编译语句
            PreparedStatement statement = connection.prepareStatement(sql);
            //3、执行语句集，并且获得结果集
            if(null != args){
                for(int i = 1; i <= args.length; i++){
                    statement.setObject(i,args[i-1]);
                }
            }

            ResultSet resultSet = statement.executeQuery();
            List list =new ArrayList<T>();
            //4、解析结果集
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }

            //5、关闭结果集
            resultSet.close();
            //6、关闭语句集
            statement.close();
            //7、关闭连接
            connection.close();

            return list;


        }catch (Exception e){
            e.printStackTrace();
        }


        return new ArrayList<T>();
    }
}
