package com.tuoyou.tavern.protocol.authcenter.dto;

import lombok.Data;

@Data
public class AuthMenuDTO {
    private Long menuId;

    private String menuUrl;

    private String menuName;

    private Long parentId;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private String updateDate;

}