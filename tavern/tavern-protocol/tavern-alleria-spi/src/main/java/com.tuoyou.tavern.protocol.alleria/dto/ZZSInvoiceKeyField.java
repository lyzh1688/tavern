package com.tuoyou.tavern.protocol.alleria.dto;

import lombok.Data;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@Data
public class ZZSInvoiceKeyField {
    private String fileId;
    private String invoiceCode;
    private String invoiceId;
    private String invoiceDate;
    private String checkCode;
    private String taxFreeAmount;
}
