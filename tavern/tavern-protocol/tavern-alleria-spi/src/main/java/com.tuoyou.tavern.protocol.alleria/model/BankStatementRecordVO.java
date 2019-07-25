package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class BankStatementRecordVO implements Serializable {

    private String fileId;

    private String batchId;

    private String fileName;

    private String accountPeriod;

    private String isValid;

    private String updateDate;

    private String customId;

    private String bankId;

}