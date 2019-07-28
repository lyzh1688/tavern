package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BankStatementDtlCcblRecordVO  {

    private String detailId;

    private String customAccntId;

    private String customAccntName;

    private String tradeDate;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private BigDecimal balance;

    private String coinCode;

    private String recvAccntId;

    private String recvAccntName;

    private String institution;

    private String accountingDate;

    private String companySeq;

    private String certificateType;

    private String certificateId;

    private String fileId;

    private String abstracts;

    private String remark;
}