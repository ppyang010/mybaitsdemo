package com.ccy.mybatis.v1;

import java.util.List;

public interface CyExecutor {
    List query(String statement , Object[] parameter);
}
