package com.tuoyou.tavern.alleria.domain;

import java.util.Date;

public class StdInvoiceRecord {
    private String fileId;

    private String batchId;

    private String fileName;

    private Date accountPeriod;

    private String isValid;

    private String updateDate;

    private String customId;

    private String financeDiskType;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Date getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(Date accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public String getFinanceDiskType() {
        return financeDiskType;
    }

    public void setFinanceDiskType(String financeDiskType) {
        this.financeDiskType = financeDiskType == null ? null : financeDiskType.trim();
    }
}