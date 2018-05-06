package com.ccy.mybatis.v2.executor;

import com.ccy.mybatis.v2.config.CyConfigurationV2;

/**
 *  Executor 静态工厂
 */
public class ExecutorFactory {

    private static final String SIMPLE = "SIMPLE";
    private static final String CACHING = "CACHING";

    public static CyExecutorV2 DEFAULT(CyConfigurationV2 configuration) {
        return get(ExecutorType.CACHING.name(), configuration);
    }

    public static CyExecutorV2 get(String key, CyConfigurationV2 configuration) {
        if (SIMPLE.equalsIgnoreCase(key)) {
            return new SimpleCyExecutorV2(configuration);
        }
        if (CACHING.equalsIgnoreCase(key)) {
            return new CacheCyExecutorV2(new SimpleCyExecutorV2(configuration));
        }
        throw new RuntimeException("no executor found");
    }

    public enum ExecutorType {
        SIMPLE,CACHING
    }
}
