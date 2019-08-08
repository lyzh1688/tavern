package com.tuoyou.tavern.protocol.hrm.model;

import lombok.Data;

@Data
public class HrmRoleAuth {
    private String roleId;

    private String item;

    private String authType;

    private String itemType;

}