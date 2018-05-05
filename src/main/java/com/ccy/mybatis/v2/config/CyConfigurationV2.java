package com.ccy.mybatis.v2.config;

import com.ccy.mybatis.v1.CySqlSession;
import com.ccy.mybatis.v1.MapperProxy;
import com.ccy.mybatis.v2.Mapper.MapperProxyV2;
import com.ccy.mybatis.v2.session.CySqlSessionV2;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CyConfigurationV2 {
    private CyMapperRegistryV2 cyMapperRegistryV2 =new CyMapperRegistryV2();

    public <T> T getMapper(Class clazz,CySqlSessionV2 sqlSession){
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxyV2(sqlSession));
    }


    public CyMapperRegistryV2.MapperData getMapperData(String nameSpace){
        return cyMapperRegistryV2.get(nameSpace);
    }


}
