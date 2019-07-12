package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_EXTRA_INVOICE_RECORD")
public class ExtraInvoiceRecord extends Model<ExtraInvoiceRecord> {

    @NotNull
    @TableId
    private String recordId;

    private String customId;

    private LocalDateTime accountPeriod;

    private String invoiceType;

    private BigDecimal totalAmount;

    private Long itemCount;

    private String isValid;

}