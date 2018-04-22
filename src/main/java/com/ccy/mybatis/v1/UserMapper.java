package com.ccy.mybatis.v1;

import com.ccy.mybatis.demo.domain.User;

public interface UserMapper {
    User getByUsername(String username);
}
