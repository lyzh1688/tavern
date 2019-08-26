package com.tuoyou.tavern.protocol.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CrmCompanyBusinessRelDTO {
    @NotNull
    private String companyId;
    @NotNull
    private String businessId;

}