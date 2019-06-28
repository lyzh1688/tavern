package com.tuoyou.tavern.invoice.ocr.libs.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 刘悦之 on 2019/6/28.
 */
public class TBInvoiceModel {
    /*
    {"发票代码":"3100174130","发票号码":"28704021","开票日期":"2018年3月5日","校验码":"","发票金额":"892","大写金额":"892","发票税额":"129.61","不含税金额":"762.39","受票方名称":"上海超塑工贸有限公司","受票方税号":"91310110MA1G850728","受票方地址、电话":"上海市杨浦区闸殷路840号101-9室021-65377330","受票方开户行、账号":"招商银行般份有限公司上海长阳支行121920689910636","销售方名称":"上海园迈贸易有限公司","销售方税号":"91310114666025597Y","销售方地址、电话":"上海市嘉定工业区叶城路1118号19层1901室021-39915587","销售方开户行、账号":"交通银行上海嘉定支行310069079018010104673","联次":"第二联：抵扣联","发票类型":"专用发票"}
    * */
    @JSONField(name = "发票代码")
    private String invoiceCode;
    @JSONField(name = "发票号码")
    private String invoiceId;
    @JSONField(name = "开票日期")
    private String invoiceDate;
    @JSONField(name = "校验码")
    private String checkCode;
    @JSONField(name = "不含税金额")
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
