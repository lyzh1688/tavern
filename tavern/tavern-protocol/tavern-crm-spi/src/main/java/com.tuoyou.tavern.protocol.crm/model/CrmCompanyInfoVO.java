package com.tuoyou.tavern.protocol.crm.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CrmCompanyInfoVO {
    private String customId;

    private String companyName;
    private String companyId;

    private String taxType;

    private String province;

    private String city;

    private String district;

    private String financeDiskType;

    private BigDecimal taxRate;

    private List<String> banks = Lists.newArrayList();

    private String updateDate;
}