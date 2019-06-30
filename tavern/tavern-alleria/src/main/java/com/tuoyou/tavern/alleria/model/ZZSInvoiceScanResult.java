package com.tuoyou.tavern.alleria.model;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
public class ZZSInvoiceScanResult {

    String fileName;
    String fileId;
    boolean isVerified;
    boolean isEmend;
    String operator;
    String updateDate;
    String failedReason;
    String invoiceCode;
    String invoiceId;
    String checkCode;
    String taxFreeAmount;
    String invoiceDate;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

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

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isEmend() {
        return isEmend;
    }

    public void setEmend(boolean emend) {
        isEmend = emend;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }
}
