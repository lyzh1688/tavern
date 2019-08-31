package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_ORDER_BUSINESS_REL")
public class CrmOrderBusinessRel extends Model<CrmOrderBusinessRel> {

    @TableId
    private String eventId;

    private String orderId;

    private String businessId;

    private String companyId;

    private String ownerId;

    private String needThirdParty;

    private String thirdPartyId;

    private BigDecimal thirdPartyFee;

    private String businessTag;

    private String preEventId;

    private String remark;

}