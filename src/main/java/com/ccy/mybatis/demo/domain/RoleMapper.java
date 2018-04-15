package com.ccy.mybatis.demo.domain;

import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RoleMapper {


    @Select("select * from sys_roles")
    List<Role> listAll();

    Role findById(int id);

    Role findById2(int id);

    int add(Role role);

    int add2(Role role);
}
