package com.ccy.mybatis.demo.domain;

import lombok.Data;

@Data
public class User {
    /**
     * 主键和自增
     */
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private Integer locked;

    public User() {
    }

    public String getCredentialsSalt() {
        return username + salt;
    }
}
