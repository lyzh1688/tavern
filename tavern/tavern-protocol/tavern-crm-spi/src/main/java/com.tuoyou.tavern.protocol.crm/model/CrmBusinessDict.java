package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_BUSINESS_DICT")
public class CrmBusinessDict extends Model<CrmBusinessDict> {
    private String businessId;

    private String businessName;

}