package com.tuoyou.tavern.protocol.alleria.model;

import lombok.Data;

@Data
public class SalaryStatementRecordVO {
    private String batchId;

    private String fileId;

    private String fileName;

    private String accountPeriod;

    private String isValid;

    private String updateDate;

    private String customId;

}