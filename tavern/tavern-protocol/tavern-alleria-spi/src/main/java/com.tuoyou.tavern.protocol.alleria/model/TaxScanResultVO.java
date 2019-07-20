package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
public class TaxScanResultVO {

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

    private String updateDate;

    private String batchId;

    private String operator;

    private String failedReason;

    private String imageUrl;
}