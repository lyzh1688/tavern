package com.tuoyou.tavern.protocol.alleria.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExtraInvoiceRecordDTO {

    private String recordId;

    private String customId;

    private String accountPeriod;

    private String invoiceType;

    private String totalAmount;

    private String itemCount;

    private String isValid;

}