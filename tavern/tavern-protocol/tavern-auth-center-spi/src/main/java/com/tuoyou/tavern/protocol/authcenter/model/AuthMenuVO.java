package com.tuoyou.tavern.protocol.authcenter.model;

import lombok.Data;

import java.util.List;

@Data
public class AuthMenuVO {
    private Long menuId;

    private String menuUrl;

    private String menuName;

    private Long parentId;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private String updateDate;


    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;
    // 非数据库字段
    private List<AuthMenuVO> children;
}