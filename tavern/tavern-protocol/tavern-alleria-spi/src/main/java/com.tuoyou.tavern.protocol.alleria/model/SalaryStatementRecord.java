package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Date;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_SALARY_STATEMENT_RECORD")
public class SalaryStatementRecord extends Model<SalaryStatementRecord> {
    private String batchId;

    @NonNull
    @TableId
    private String fileId;

    private String fileName;

    private Date accountPeriod;

    private String isValid;

    private String updateDate;

    private String customId;
}