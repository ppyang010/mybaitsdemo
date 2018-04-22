package com.ccy.mybatis.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CyConfiguration {


    public <T> T getMapper(Class clazz,CySqlSession sqlSession){
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(sqlSession));
    }

    /**
     * 模拟mapper xml解析出来 实体类
     * 这里只展现了 namespace and sql部分
     * xml解析好了
     */
    static class UserMapperXml{
        public static final String namespace = "com.ccy.mybatis.v1.UserMapper";

        public static final Map<String,String> methodSqlMapping = new HashMap<>();

        static {
            methodSqlMapping.put("getByUsername","select * from sys_users where username = ?");
        }
    }
}
