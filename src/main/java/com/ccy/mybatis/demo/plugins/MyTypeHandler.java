package com.ccy.mybatis.demo.plugins;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@MappedJdbcTypes({JdbcType.VARCHAR})
//@MappedTypes({String.class})
public class MyTypeHandler extends BaseTypeHandler<String> {
    //此处如果不用注解指定jdbcType, 那么，就可以在配置文件中通过"jdbcType"属性指定， 同理， javaType 也可通过 @MappedTypes指定
//    1.@MappedJdbcTypes定义的是JdbcType类型，这里的类型不可自己随意定义，必须要是枚举类org.apache.ibatis.type.JdbcType所枚举的数据类型
        //定义了这个之后会覆盖对应的jdbctype -> java type默认的handler  简单来说是select的时候
        //总的来说定义两个注解后 mybatis会根据两个类型来选择typehandler 并会有优先选择自定义的
//    2.@MappedTypes定义的是JavaType的数据类型，描述了哪些Java类型可被拦截。
    //因为BaseTypeHandler继承 TypeReference 相当于 BaseTypeHandler中指定的泛型相当于有了对应的MappedTypes
//    3.在我们启用了我们自定义的这个TypeHandler之后，数据的读写都会被这个类所过滤
//    4.在setNonNullParameter方法中，我们重新定义要写往数据库的数据。
//    5.在另外三个方法中我们将从数据库读出的数据类型进行转换。

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,s+"ccy");
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        int index = str.lastIndexOf("ccy");
        if(index>0){
            str = str.substring(0,index);
        }
        return str;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        int index = str.lastIndexOf("ccy");
        if(index>0){
            str = str.substring(0,index);
        }
        return str;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        int index = str.lastIndexOf("ccy");
        if(index>0){
            str = str.substring(0,index);
        }
        return str;
    }
}
