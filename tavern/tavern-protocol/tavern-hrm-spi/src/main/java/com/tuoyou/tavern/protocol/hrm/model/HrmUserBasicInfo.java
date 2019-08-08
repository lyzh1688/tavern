package com.tuoyou.tavern.protocol.hrm.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class HrmUserBasicInfo {
    private String userId;

    private String userName;

    private String userAccnt;

    private String password;

    private String isValid;

    private LocalDateTime updateDate;

}