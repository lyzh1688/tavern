package com.tuoyou.tavern.alleria.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@TableName("ALR_VALUE_ADDED_TAX_SCAN_RESULT")
public class TaxScanResult extends Model<TaxScanResult> {

    @TableId
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

    private LocalDateTime updateDate;

    private String batchId;

    private String operator;

    private String failedReason;
}