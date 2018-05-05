package com.ccy.mybatis.v2.executor;

import com.ccy.mybatis.v1.DBTool;

import java.util.List;

public class SimpleCyExecutorV2 implements CyExecutorV2 {
    @Override
    public List query(String statement, Object[] parameter) {
        return DBTool.execute(statement,parameter);
    }
}
