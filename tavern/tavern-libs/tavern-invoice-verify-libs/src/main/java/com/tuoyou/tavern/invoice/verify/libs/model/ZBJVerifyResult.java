package com.tuoyou.tavern.invoice.verify.libs.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public class ZBJVerifyResult {

    @JSONField(name = "success")
    Boolean isSuccess;

    @JSONField(name = "code")
    int retCode;

    @JSONField(name = "message")
    String message;

    @JSONField(name = "description")
    String desc;

    @JSONField(name = "data")
    ZBJInvoiceData invoiceData;

    public ZBJInvoiceData getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(ZBJInvoiceData invoiceData) {
        this.invoiceData = invoiceData;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
