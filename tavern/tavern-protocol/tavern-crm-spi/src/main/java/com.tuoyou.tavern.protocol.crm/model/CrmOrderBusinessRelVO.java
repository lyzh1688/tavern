package com.tuoyou.tavern.protocol.crm.model;

import com.tuoyou.tavern.protocol.crm.dto.DljzDetail;
import com.tuoyou.tavern.protocol.crm.dto.GjjsbdjDetail;
import com.tuoyou.tavern.protocol.crm.dto.GszcDetail;
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

    //代理记账
    private String dljzBeginDate;
    private String dljzEndDate;
    private String dljzDiff;
    private String isBegin;

    //公积金代缴/社保代缴
    private String gjjsbdjBeginDate;
    private String gjjsbdjEndDate;
    private String gjjsbdjDiff;
    private int employeeNum;
    //公司注册
    private String absent;
    private String regLocationType;

}