package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("crm_custom_third_party_info")
public class CrmThirdPartyInfo extends Model<CrmThirdPartyInfo> {
    private String thirdPartyId;

    private String thirdPartyName;

}