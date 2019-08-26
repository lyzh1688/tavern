package com.tuoyou.tavern.protocol.crm.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CrmCompanyStaffInfoVO {

    private String companyId;

    private String staffId;

    private String name;

    private String idCard;

    private String contactNumber;

    private String needSocialIns;

    private String needHabitationIns;

    private String needSocialServer;

    private String needHabitationServer;

    private String updateDate;
}