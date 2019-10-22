package com.tuoyou.tavern.protocol.crm.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrmCustomOrderInfoVO {
    private Long id;

    private String orderId;

    private String customId;

    private String orderDate;

    private BigDecimal receivableAmt;

    private BigDecimal payableAmt;

    private String orderSource;

    private String businessName;

}