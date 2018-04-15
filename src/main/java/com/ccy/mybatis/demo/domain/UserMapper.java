package com.ccy.mybatis.demo.domain;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 注解sql，复杂的实现百度下
    @Select("select * from sys_users")
    List<User> listAll();

    //sql在xml中
    User findById(int id);
}
