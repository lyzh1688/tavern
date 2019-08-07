package com.tuoyou.tavern.crm.entity;

import java.util.Date;

public class CrmCustomFinanceInfo {
    private String customId;

    private String financeDiskType;

    private Date updateDate;

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}