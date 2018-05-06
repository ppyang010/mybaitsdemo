package com.ccy.mybatis.v2.config;

import com.ccy.mybatis.v2.Mapper.MapperProxyV2;
import com.ccy.mybatis.v2.executor.CyExecutorV2;
import com.ccy.mybatis.v2.executor.ExecutorFactory;
import com.ccy.mybatis.v2.result.ResultSetHandlerV2;
import com.ccy.mybatis.v2.session.CySqlSessionV2;
import com.ccy.mybatis.v2.statement.StatementHandlerV2;

import java.lang.reflect.Proxy;

/**
 * 配置信息类
 */
public class CyConfigurationV2 {
    private CyMapperRegistryV2 cyMapperRegistryV2 =new CyMapperRegistryV2();

    private String defaultExecutorType = ExecutorFactory.ExecutorType.CACHING.name();
    public CyConfigurationV2() {
    }

    public CyConfigurationV2(String executorType) {
        this.defaultExecutorType = executorType;
    }

    public <T> T getMapper(Class clazz, CySqlSessionV2 sqlSession){
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxyV2(sqlSession));
    }


    public CyMapperRegistryV2.MapperData getMapperData(String nameSpace){
        return cyMapperRegistryV2.get(nameSpace);
    }

    public StatementHandlerV2 newStatementHandler(ResultSetHandlerV2 resultSetHandlerV2){
        return new StatementHandlerV2(resultSetHandlerV2);
    }

    public CyExecutorV2 getExecutor(){
        return getExecutor(defaultExecutorType);
    }

    public CyExecutorV2 getExecutor(String excutorType){
        return ExecutorFactory.get(excutorType,this);
    }
}
