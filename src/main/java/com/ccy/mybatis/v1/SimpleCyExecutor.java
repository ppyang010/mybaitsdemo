package com.ccy.mybatis.v1;

import java.util.List;

public class SimpleCyExecutor implements CyExecutor {
    @Override
    public List query(String statement, Object[] parameter) {
        return DBTool.execute(statement,parameter);
    }
}
