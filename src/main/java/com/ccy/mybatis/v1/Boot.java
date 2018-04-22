package com.ccy.mybatis.v1;

import com.ccy.mybatis.demo.domain.User;

/**
 * 测试类
 */
public class Boot {

    public static void main(String[] args) {
        CySqlSession sqlSession = new CySqlSession(new CyConfiguration(), new SimpleCyExecutor());
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User admin = mapper.getByUsername("admin");
        System.out.println(admin);
    }
}
