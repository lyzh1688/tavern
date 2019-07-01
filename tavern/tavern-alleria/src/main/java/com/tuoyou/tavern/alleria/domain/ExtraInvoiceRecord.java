package com.tuoyou.tavern.alleria.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ExtraInvoiceRecord {
    private String recordId;

    private String customId;

    private Date accountPeriod;

    private String invoiceType;

    private BigDecimal totalAmount;

    private Long itemCount;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public Date getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(Date accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }
}