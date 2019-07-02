package com.tuoyou.tavern.alleria.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_STD_INVOICE_RECORD")
public class StdInvoiceRecord extends Model<StdInvoiceRecord> {
    @TableId
    private String fileId;
    @TableId
    private String batchId;

    private String fileName;

    private LocalDateTime accountPeriod;

    private String isValid;

    private String updateDate;

    private String customId;

    private String financeDiskType;


}