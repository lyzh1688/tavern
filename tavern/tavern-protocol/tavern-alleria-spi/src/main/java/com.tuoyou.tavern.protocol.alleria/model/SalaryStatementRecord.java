package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_SALARY_STATEMENT_RECORD")
public class SalaryStatementRecord extends Model<SalaryStatementRecord> {
    private String batchId;

    @TableId
    private String fileId;

    private String fileName;

    private LocalDateTime accountPeriod;

    private String isValid;

    private LocalDateTime updateDate;

    private String customId;

}