package com.tuoyou.tavern.alleria.model;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
public class ZZSInvoiceKeyField {
    String invoiceCode;
    String invoiceId;
    String invoiceDate;
    String checkCode;
    String taxFreeAmount;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getTaxFreeAmount() {
        return taxFreeAmount;
    }

    public void setTaxFreeAmount(String taxFreeAmount) {
        this.taxFreeAmount = taxFreeAmount;
    }
}
