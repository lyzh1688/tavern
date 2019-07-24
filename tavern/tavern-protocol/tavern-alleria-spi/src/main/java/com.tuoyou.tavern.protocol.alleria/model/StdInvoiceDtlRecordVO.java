package com.tuoyou.tavern.protocol.alleria.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class StdInvoiceDtlRecordVO implements Serializable {
    private String fileId;

    private String invoiceId;

    private String invoiceCode;

    private String buyerName;

    private String buyerTaxCode;

    private String bankBranch;

    private String bankAccount;

    private String address;

    private String tel;

    private String invoiceDate;

    private String productVersion;

    private String receiptId;

    private String productName;

    private String productSpec;

    private String productUnit;

    private BigDecimal productCount;

    private BigDecimal productUnitPrice;

    private BigDecimal totalPrice;

    private String taxRate;

    private BigDecimal tax;

    private String taxTypeCode;

    private String imageUrl;



}