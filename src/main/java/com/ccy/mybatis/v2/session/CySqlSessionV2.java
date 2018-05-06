package com.ccy.mybatis.v2.session;

import com.ccy.mybatis.v2.config.CyConfigurationV2;
import com.ccy.mybatis.v2.config.CyMapperRegistryV2;
import com.ccy.mybatis.v2.executor.CyExecutorV2;
import com.ccy.mybatis.v2.executor.SimpleCyExecutorV2;

import java.util.List;

public class CySqlSessionV2 {
    private CyConfigurationV2 configuration;

    private CyExecutorV2 executor;

    public CySqlSessionV2(CyConfigurationV2 configuration) {
        this.configuration = configuration;
        this.executor = configuration.getExecutor();
    }

    public <T> T getMapper(Class clazz){
        return  configuration.getMapper(clazz,this);
    }


    public CyMapperRegistryV2.MapperData getMapperData(String nameSpace){
        return configuration.getMapperData(nameSpace);
    }

    public <T> T selectOne(CyMapperRegistryV2.MapperData data, Object[] args) {
        List list = executor.query(data, args);
        if(list.size()>0){
            return (T) list.get(0);
        }
        return null;
    }


    public <T> List selectList(CyMapperRegistryV2.MapperData data, Object[] args) {
        List list = executor.query(data, args);
        return list;
    }
}
