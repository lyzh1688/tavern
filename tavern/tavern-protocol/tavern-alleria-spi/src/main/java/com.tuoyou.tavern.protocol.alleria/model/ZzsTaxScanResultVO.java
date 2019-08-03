package com.tuoyou.tavern.protocol.alleria.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ZzsTaxScanResultVO implements Serializable {

    private String fileId;

    private String fileName;

    private String isValid;

    private String isVerified;

    private String isEmend;

    private String invoiceId;

    private String invoiceCode;

    private String invoiceDate;

    private String checkCode;

    private String taxFreeAmount;

    private String updateDate;

    private String batchId;

    private String operator;

    private String failedReason;

    private String imageUrl;

    private String invoiceType;

    private String sellerName;

    private String sellerTaxCode;

    private String sellerBank;

    private String buyerName;

    private String buyerId;

    private String buyerContact;

    private String buyerBank;

    private String invoiceMachineNum;

    private String isInvalid;

    private String taxAmount;

    private String totalAmount;

    private String quantity;

    private String remark;

    private String verifyTimes;

    private String updateTime;

    private List<ZBJInvoiceItemVO> goodsData;

    private List<ZBJInvoiceItemVO> children;
}