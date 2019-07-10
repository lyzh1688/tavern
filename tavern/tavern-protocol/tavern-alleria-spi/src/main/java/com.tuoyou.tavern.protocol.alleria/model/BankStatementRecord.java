package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_BANK_STATEMENT_RECORD")
public class BankStatementRecord extends Model<BankStatementRecord> {
    private String fileId;

    private String batchId;

    private String fileName;

    private LocalDateTime accountPeriod;

    private String isValid;

    private String updateDate;

    private String customId;

    private String bankId;

}