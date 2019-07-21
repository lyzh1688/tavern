package com.tuoyou.tavern.protocol.alleria.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StdInvoiceRecordVO implements Serializable {

    private String fileId;
    private String batchId;

    private String fileName;

    private String accountPeriod;

    private String isValid;

    private String updateDate;

    private String customId;

    private String financeDiskType;


}