package com.ccy.mybatis.v2.executor;

import com.ccy.mybatis.v2.config.CyMapperRegistryV2;

import java.util.List;

public interface CyExecutorV2 {

    abstract <E> E query(CyMapperRegistryV2.MapperData data, Object[] parameter);
}
