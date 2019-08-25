package com.tuoyou.tavern.protocol.crm.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class CrmCompanyRelDTO {
    @NotNull
    private String customId;
    @NotNull
    private String companyId;

}