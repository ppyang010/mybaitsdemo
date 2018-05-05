package com.ccy.mybatis.v2.executor;

import java.util.List;

public interface CyExecutorV2 {
    List query(String statement, Object[] parameter);
}
