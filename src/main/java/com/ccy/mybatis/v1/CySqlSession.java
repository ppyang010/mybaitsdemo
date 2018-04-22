package com.ccy.mybatis.v1;

import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Proxy;
import java.util.List;

public class CySqlSession {
    private CyConfiguration configuration;

    private  CyExecutor executor;

    public CySqlSession(CyConfiguration configuration, CyExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class clazz){
        return  configuration.getMapper(clazz,this);
    }

    public <T> T selectOne(String sql, Object[] args) {
        List list = executor.query(sql, args);
        if(list.size()>0){
            return (T) list.get(0);
        }
        return null;
    }
}
