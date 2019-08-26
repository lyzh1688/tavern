package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_COMPANY_STAFF_REL")
public class CrmCompanyStaffRel extends Model<CrmCompanyStaffRel> {
    @TableId
    private String companyId;
    @TableId
    private String staffId;

}