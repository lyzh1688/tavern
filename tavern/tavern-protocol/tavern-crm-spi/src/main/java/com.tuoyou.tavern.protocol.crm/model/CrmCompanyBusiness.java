package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_COMPANY_BUSINESS")
public class CrmCompanyBusiness extends Model<CrmCompanyBusiness> {
    @TableId
    private String companyId;
    @TableId
    private String businessId;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private LocalDateTime updateDate;

    private String isValid;
}