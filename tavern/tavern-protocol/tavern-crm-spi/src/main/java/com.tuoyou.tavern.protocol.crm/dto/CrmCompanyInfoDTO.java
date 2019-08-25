package com.tuoyou.tavern.protocol.crm.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CrmCompanyInfoDTO {
    @NotNull
    private String customId;

    private String companyId;

    @NotNull
    private String companyName;

    @NotNull
    private String taxType;

    private List<String> area = Lists.newArrayList();

    private String financeDiskType;

    private String taxRate;

    private List<String> banks = Lists.newArrayList();

    private String isValid;

}