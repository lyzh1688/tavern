package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_STD_INVOICE_DTL_RECORD")
public class StdInvoiceDtlRecord extends Model<StdInvoiceDtlRecord> {
    @TableId
    private String fileId;

    @TableId
    private String invoiceId;

    @TableId
    private String invoiceCode;

    private String buyerName;

    private String buyerTaxCode;

    private String bankBranch;

    private String bankAccount;

    private String address;

    private String tel;

    private LocalDateTime invoiceDate;

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