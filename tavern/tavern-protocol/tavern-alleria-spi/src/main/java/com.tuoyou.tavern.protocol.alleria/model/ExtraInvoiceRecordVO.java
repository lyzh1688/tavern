package com.tuoyou.tavern.protocol.alleria.model;

import lombok.Data;

@Data
public class ExtraInvoiceRecordVO {

    private String recordId;

    private String customId;

    private String accountPeriod;

    private String invoiceType;

    private String totalAmount;

    private String itemCount;

    private String isValid;

}