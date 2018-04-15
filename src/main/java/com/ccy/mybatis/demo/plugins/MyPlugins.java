package com.ccy.mybatis.demo.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * 简单插件
 * 对预编译拦截?
 */
@Intercepts( {@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class,Integer.class}) })
public class MyPlugins implements Interceptor {

//    （1）方法plugin(Object target)
//    plugin方法是拦截器用于封装目标对象的，通过该方法我们可以返回目标对象本身，也可以返回一个它的代理。当返回的是代理的时候我们可以对其中的方法进行拦截来调用intercept方法，当然也可以调用其他方法。
//    （2）方法setProperties(Properties properties)
//    setProperties方法是用于在Mybatis配置文件中指定一些属性的。
//    （3）方法intercept(Invocation invocation)
//    定义自己的Interceptor最重要的是要实现plugin方法和intercept方法，在plugin方法中我们可以决定是否要进行拦截进而决定要返回一个什么样的目标对象。而intercept方法就是要进行拦截的时候要执行的方法。
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        System.out.println( "sql is " + boundSql.getSql());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        //dialect 方言
        String dialect = properties.getProperty("dialect");
        System.out.println("dialect="+dialect);
    }
}
