package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_COMPANY_INFO")
public class CrmCompanyInfo extends Model<CrmCompanyInfo> {
    @TableId
    private String companyId;

    private String companyName;

    private String taxType;

    private String province;
    private String city;

    private String district;

    private String financeDiskType;

    private BigDecimal taxRate;

    private LocalDateTime updateDate;
}