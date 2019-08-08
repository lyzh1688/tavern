package com.tuoyou.tavern.protocol.hrm.model;

import lombok.Data;

@Data
public class HrmRoleInfo {
    private String roleId;

    private String roleName;

    private String parentRole;

}