package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_COMPANY_BANK_REL")
public class CrmCompanyBankRel extends Model<CrmCompanyBankRel> {
    @TableId
    private String companyId;
    @TableId
    private String bankId;

}