package com.tuoyou.tavern.protocol.crm.model.workflow;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/04 <br>
 */
@Data
public class MyTodoListVO {
    private String orderId;
    private BigDecimal receivableAmt;
    private BigDecimal payableAmt;
    private String businessId;
    private String eventId;
    private String customName;
    private String wangwangAccnt;
    private String companyName;
    private String companyId;
    private String city;
    private String district;
    private String remark;
    private String weixinName;
    private String businessName;
    private String businessInfo;
    private String cost;
    private String maxLeftDay;
    private String businessTag;
    private String createDate;
    private String customLevel;
    private String curNodeName;
    private String curNodeId;
    private String curOperatorName;
    //售前
    private String preSalesId;
    private String preSales;
    //对接人
    private String ownerId;
    private String owner;
    private String thirdPartyInfo;
}
