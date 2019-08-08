package com.tuoyou.tavern.protocol.hrm.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class HrmUserRoleRel {
    private String userId;

    private String roleId;

    private LocalDateTime updateDate;

}