package com.tuoyou.tavern.protocol.hrm.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HrmUserRoleRelVO  {

    private String roleName;

    private String userId;

    private String roleId;

    private LocalDateTime updateDate;

}