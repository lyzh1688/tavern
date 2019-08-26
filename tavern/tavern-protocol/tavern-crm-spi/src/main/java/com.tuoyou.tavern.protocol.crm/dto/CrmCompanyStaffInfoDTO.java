package com.tuoyou.tavern.protocol.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CrmCompanyStaffInfoDTO {

    @NotNull(message = "公司ID不能为空")
    private String companyId;

    private String staffId;

    private String name;

    private String idCard;

    private String contactNumber;

    private String needSocialIns;

    private String needHabitationIns;

    private String needSocialServer;

    private String needHabitationServer;
}