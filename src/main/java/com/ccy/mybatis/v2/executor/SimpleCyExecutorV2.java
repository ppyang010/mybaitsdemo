package com.ccy.mybatis.v2.executor;

import com.ccy.mybatis.v2.config.CyConfigurationV2;
import com.ccy.mybatis.v2.config.CyMapperRegistryV2;
import com.ccy.mybatis.v2.result.ResultSetHandlerV2;
import com.ccy.mybatis.v2.statement.StatementHandlerV2;

import java.util.List;

public class SimpleCyExecutorV2 implements CyExecutorV2 {
    private CyConfigurationV2 configuration;

    public SimpleCyExecutorV2(CyConfigurationV2 configuration) {
        this.configuration = configuration;
    }
    @Override
    public <E> E query(CyMapperRegistryV2.MapperData data, Object[] parameter) {
        //todo 这里的resultsethandler 可以继续跟进
        StatementHandlerV2 handler = configuration.newStatementHandler(new ResultSetHandlerV2());
        return (E) handler.query(data, parameter);
    }
}
