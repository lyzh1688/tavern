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

    private String province;

    private String city;

    private String district;

    private String address;

    private String deliveryAddr;

    private String contact1;

    private String contactNumber1;

    private String contactWechat1;

    private String contact2;

    private String contactNumber2;

    private String contactWechat2;


}