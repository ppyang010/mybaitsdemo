package com.ccy.mybatis.v2.Mapper;

import com.ccy.mybatis.v2.config.CyMapperRegistryV2;
import com.ccy.mybatis.v2.session.CySqlSessionV2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxyV2 implements InvocationHandler {
//    private CyConfiguration configuration;
    private CySqlSessionV2 sqlSession;


    public MapperProxyV2(CySqlSessionV2 sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        CyMapperRegistryV2.MapperData mapperData = sqlSession.getMapperData(method.getDeclaringClass().getName() + "." + method.getName());
        if(null != mapperData){
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", mapperData.getSql(), args[0]));
            return sqlSession.selectOne(mapperData.getSql(), args);
        }
        return null;
    }
}
