package com.tuoyou.tavern.alleria.domain;

public class InvoiceTypeDict {
    private String invoiceType;

    private String invoiceTypeName;

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getInvoiceTypeName() {
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName == null ? null : invoiceTypeName.trim();
    }
}