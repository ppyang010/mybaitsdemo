package com.ccy.mybatis.v2.Mapper;

import com.ccy.mybatis.v2.config.CyMapperRegistryV2;
import com.ccy.mybatis.v2.session.CySqlSessionV2;
import org.apache.ibatis.cursor.Cursor;

import java.awt.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

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
            //这里参考 org.apache.ibatis.binding.MapperMethod.execute
            if (void.class.equals(method.getReturnType())) {
                return null;
            } else if (List.class.equals(method.getReturnType())) {
               return sqlSession.selectList(mapperData, args);
            }
//            else if (method.returnsMap()) {
//                result = executeForMap(sqlSession, args);
//            } else if (method.returnsCursor()) {
//                result = executeForCursor(sqlSession, args);
//            }
            else {
                return sqlSession.selectOne(mapperData, args);
            }

        }
        return null;
    }
}
