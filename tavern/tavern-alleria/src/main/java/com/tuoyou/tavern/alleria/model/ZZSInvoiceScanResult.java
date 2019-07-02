package com.tuoyou.tavern.alleria.model;

import lombok.Data;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@Data
public class ZZSInvoiceScanResult {

    private String fileName;
    private String fileId;
    private boolean isVerified;
    private boolean isEmend;
    private String operator;
    private String updateDate;
    private String failedReason;
    private String invoiceCode;
    private String invoiceId;
    private String checkCode;
    private String taxFreeAmount;
    private String invoiceDate;
}
