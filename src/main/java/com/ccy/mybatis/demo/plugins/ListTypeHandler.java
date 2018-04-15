package com.ccy.mybatis.demo.plugins;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

//@MappedJdbcTypes({JdbcType.VARCHAR})
//@MappedTypes({String.class})
public class ListTypeHandler extends BaseTypeHandler<List> {

    //    1.@MappedJdbcTypes定义的是JdbcType类型，这里的类型不可自己随意定义，必须要是枚举类org.apache.ibatis.type.JdbcType所枚举的数据类型
    //定义了这个之后会覆盖对应的jdbctypc默认的handler  下面的不会覆盖
//    2.@MappedTypes定义的是JavaType的数据类型，描述了哪些Java类型可被拦截。
//    3.在我们启用了我们自定义的这个TypeHandler之后，数据的读写都会被这个类所过滤
//    4.在setNonNullParameter方法中，我们重新定义要写往数据库的数据。
//    5.在另外三个方法中我们将从数据库读出的数据类型进行转换。

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,String.join(",",list));
    }

    @Override
    public List getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        String[] split = string.split(",");
        return Arrays.asList(split);
    }

    @Override
    public List getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        String[] split = string.split(",");
        return Arrays.asList(split);
    }

    @Override
    public List getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        String[] split = string.split(",");
        return Arrays.asList(split);
    }



}
