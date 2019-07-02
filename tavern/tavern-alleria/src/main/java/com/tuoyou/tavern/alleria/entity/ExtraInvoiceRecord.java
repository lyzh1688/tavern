package com.tuoyou.tavern.alleria.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_EXTRA_INVOICE_RECORD")
public class ExtraInvoiceRecord extends Model<ExtraInvoiceRecord> {

    @TableId
    private String recordId;

    private String customId;

    private LocalDateTime accountPeriod;

    private String invoiceType;

    private BigDecimal totalAmount;

    private Long itemCount;

}