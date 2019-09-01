package com.tuoyou.tavern.protocol.crm.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CrmOrderBusinessRelVO {

    private String eventId;

    private String orderId;

    private String businessId;

    private String businessName;

    private String companyId;

    private String companyName;

    private String ownerId;

    private String owner;

    private String needThirdParty;

    private String thirdPartyId;

    private BigDecimal thirdPartyFee;

    private String businessTag;

    private String preEventId;

    private String remark;

    private String createDate;


}