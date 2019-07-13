package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_INVOICE_TYPE_DICT")
public class InvoiceTypeDict extends Model<InvoiceTypeDict> {
    @NonNull
    @TableId
    private String invoiceType;

    private String invoiceTypeName;
}