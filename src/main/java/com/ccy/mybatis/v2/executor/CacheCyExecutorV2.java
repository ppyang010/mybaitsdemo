package com.ccy.mybatis.v2.executor;

import com.ccy.mybatis.v2.config.CyConfigurationV2;
import com.ccy.mybatis.v2.config.CyMapperRegistryV2;
import com.ccy.mybatis.v2.result.ResultSetHandlerV2;
import com.ccy.mybatis.v2.statement.StatementHandlerV2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheCyExecutorV2 implements CyExecutorV2 {
    private Map<String,Object> localCache = new HashMap();

    private CyExecutorV2 delegate;

    public CacheCyExecutorV2(CyExecutorV2 delegate) {
        this.delegate = delegate;
    }
    @Override
    public <E> E query(CyMapperRegistryV2.MapperData data, Object[] parameter) {
        Object result = localCache.get(data.getSql());
        if(result == null){
            result = delegate.query(data, parameter);
            localCache.put(data.getSql(),result);
        }else{
            System.out.println("缓存命中");
        }
        return (E) result;
    }
}
