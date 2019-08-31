package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_CUSTOM_THIRD_PARTY_INFO")
public class CrmThirdPartyInfo extends Model<CrmThirdPartyInfo> {
    @TableId
    private String thirdPartyId;

    private String thirdPartyName;

}