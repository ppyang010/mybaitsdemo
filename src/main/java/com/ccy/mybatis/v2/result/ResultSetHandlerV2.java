package com.ccy.mybatis.v2.result;


import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * sql执行结果映射到对应的对象中
 */
public class ResultSetHandlerV2 {
    public <E> E handle(ResultSet rs, Class type) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //这里偷懒直接使用mybatis中的方法
        Object resultObj = new DefaultObjectFactory().create(type);
//        if (rs.next()) {
        int i = 0;
        for (Field field : resultObj.getClass().getDeclaredFields()) {
            setValue(resultObj, field, rs ,i);
        }
//        }
        return (E) resultObj;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs, int i) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field,rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //TODO type handles
        //bean属性的名字必须要和数据库column的名字一样
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }
        if(String.class == type){
            return rs.getString(field.getName());
        }
        return rs.getString(field.getName());
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
