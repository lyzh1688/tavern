package com.tuoyou.tavern.protocol.crm.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrmOrderBusinessRelVO {

    private String eventId;

    private String orderId;

    private String businessId;

    private String businessName;

    private String business;

    private String companyId;

    private String companyName;
    private String company;

    private String ownerId;

    private String owner;

    private String needThirdParty;

    private String thirdPartyId;
    private String thirdParty;

    private BigDecimal thirdPartyFee;

    private String businessTag;

    private String preEventId;
    private String preEvent;

    private String remark;

    private String createDate;

    //代理记账
    private CrmOrderBusinessDljzVO dlfw;

    //公积金代缴/社保代缴
    private CrmOrderBusinessDjfwVO djfw;
    //公司注册
    private CrmOrderBusinessGszcVO gszc;
}