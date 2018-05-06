package com.ccy.mybatis.v2;

import com.ccy.mybatis.demo.domain.User;
import com.ccy.mybatis.v2.config.CyConfigurationV2;
import com.ccy.mybatis.v2.dao.UserMapperV2;
import com.ccy.mybatis.v2.executor.SimpleCyExecutorV2;
import com.ccy.mybatis.v2.session.CySqlSessionV2;


public class BootV2 {
    public static void main(String[] args) {

        CySqlSessionV2 sqlSession = new CySqlSessionV2(new CyConfigurationV2());
        UserMapperV2 mapper = sqlSession.getMapper(UserMapperV2.class);
        User admin = mapper.getByUsername("admin");
        admin = mapper.getByUsername("admin");
        System.out.println(admin);
    }
}
