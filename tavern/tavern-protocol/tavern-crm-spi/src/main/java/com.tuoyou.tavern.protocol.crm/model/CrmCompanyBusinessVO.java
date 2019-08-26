package com.tuoyou.tavern.protocol.crm.model;

import lombok.Data;

@Data
public class CrmCompanyBusinessVO {

    private String companyId;

    private String businessId;

    private String businessName;

    private String beginDate;

    private String endDate;

    private String updateDate;

    private String isValid;
}