package com.ccy.mybatis.demo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Role {

    private Integer id;

    private String role;

    private String description;
    //是否可用
    private Integer available;

    //测试listTypeHandler
    private List<String>  descriptionList;
}
