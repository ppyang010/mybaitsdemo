package com.ccy.mybatis.v2.dao;

import com.ccy.mybatis.demo.domain.User;

public interface UserMapperV2 {
    User getByUsername(String username);
}
