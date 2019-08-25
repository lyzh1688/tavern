package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_CUSTOM_COMPANY_REL")
public class CrmCustomCompanyRel extends Model<CrmCustomCompanyRel> {
    @TableId
    private String customId;
    @TableId
    private String companyId;

}