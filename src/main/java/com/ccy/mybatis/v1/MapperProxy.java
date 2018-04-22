package com.ccy.mybatis.v1;

import org.apache.ibatis.session.SqlSession;

import javax.security.auth.login.Configuration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
//    private CyConfiguration configuration;
    private CySqlSession sqlSession;


    public MapperProxy(CySqlSession sqlSession) {

        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, String> methodSqlMapping = CyConfiguration.UserMapperXml.methodSqlMapping;
        if(method.getDeclaringClass().getName().equals(CyConfiguration.UserMapperXml.namespace)){
            String sql = methodSqlMapping.get(method.getName());
            return  sqlSession.selectOne(sql,  args);
        }
        return null;
    }
}
