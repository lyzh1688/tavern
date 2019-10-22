package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_USER_CUSTOMER_INFO")
public class CrmUserCustomerInfo extends Model<CrmUserCustomerInfo> {
    @TableId
    private String customerId;
    @TableId
    private String userId;

}