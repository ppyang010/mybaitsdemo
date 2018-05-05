package com.ccy.mybatis.v2.config;

import com.ccy.mybatis.demo.domain.User;

import java.util.HashMap;
import java.util.Map;

public class CyMapperRegistryV2 {
    public static final Map<String, MapperData> methodSqlMapping = new HashMap<>();

    //使用 1. 在这里配置
    //2. Java Bean的属性名字要和数据库表的名字一致
    //todo 可以设计成扫描包 配合注解使用
    public CyMapperRegistryV2() {
        methodSqlMapping.put("com.ccy.mybatis.v2.dao.UserMapperV2.getByUsername",
                new MapperData("select * from sys_users where username = ?",User.class));
    }

    public MapperData get(String nameSpace) {
        return methodSqlMapping.get(nameSpace);
    }


    public class MapperData<T>{
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }
}
