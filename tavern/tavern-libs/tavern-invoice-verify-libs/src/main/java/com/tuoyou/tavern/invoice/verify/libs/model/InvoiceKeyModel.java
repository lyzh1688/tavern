package com.tuoyou.tavern.invoice.verify.libs.model;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public class InvoiceKeyModel {
    //发票代码
    private String invoiceCode;
    //发票号码
    private String invoiceId;
    //开票日期
    private String invoiceDate;
    //校验码
    private String checkCode;
    //不含税金额
    private String taxFreeAmount;

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
