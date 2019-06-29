package com.tuoyou.tavern.invoice.verify.libs.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public class ZBJInvoiceData {

    //发票代码
    @JSONField(name = "fpdm")
    private String invoiceCode;
    //发票号
    @JSONField(name = "fphm")
    private String invoiceId;
    //开票日期
    @JSONField(name = "kprq")
    private String invoiceDate;
    //校验码
    @JSONField(name = "code")
    private String checkCode;
    //发票类型
    @JSONField(name = "fplx")
    private String invoiceType;
    //销售方名称
    @JSONField(name = "xfMc")
    private String sellerName;
    //销售方纳税人识别号
    @JSONField(name = "xfNsrsbh")
    private String sellerTaxCode;
    //销售方开户行
    @JSONField(name = "xfBank")
    private String sellerBank;
    //购方名称
    @JSONField(name = "gfMc")
    private String buyerName;
    //购方纳税人识别号
    @JSONField(name = "gfNsrsbh")
    String buyerID;
    //购方联系方式
    @JSONField(name = "gfContact")
    String buyerContact;
    //购方开户行
    @JSONField(name = "gfBank")
    String buyerBank;
    //发票机器码
    @JSONField(name = "num")
    String invoiceMachineNum;
    //是否作废
    @JSONField(name = "del")
    String isInVaild;
    //税额合计
    @JSONField(name = "taxamount")
    String taxAmount;
    //价格合计(不含税金额)
    @JSONField(name = "goodsamount")
    private String taxFreeAmount;
    //价税合计
    @JSONField(name = "sumamount")
    private String totalAmount;
    //数量合计
    @JSONField(name = "quantityAmount")
    String quantity;
    //备注
    @JSONField(name = "remark")
    String remark;
    //税局查验次数
    @JSONField(name = "queryCount")
    String verifyTimes;
    @JSONField(name = "updateTime")
    String updateTime;
    @JSONField(name = "goodsData")
    List<ZBJInvoiceItem> items = new ArrayList<>();

    public List<ZBJInvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<ZBJInvoiceItem> items) {
        this.items = items;
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

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerTaxCode() {
        return sellerTaxCode;
    }

    public void setSellerTaxCode(String sellerTaxCode) {
        this.sellerTaxCode = sellerTaxCode;
    }

    public String getSellerBank() {
        return sellerBank;
    }

    public void setSellerBank(String sellerBank) {
        this.sellerBank = sellerBank;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getBuyerContact() {
        return buyerContact;
    }

    public void setBuyerContact(String buyerContact) {
        this.buyerContact = buyerContact;
    }

    public String getBuyerBank() {
        return buyerBank;
    }

    public void setBuyerBank(String buyerBank) {
        this.buyerBank = buyerBank;
    }

    public String getInvoiceMachineNum() {
        return invoiceMachineNum;
    }

    public void setInvoiceMachineNum(String invoiceMachineNum) {
        this.invoiceMachineNum = invoiceMachineNum;
    }

    public String getIsInVaild() {
        return isInVaild;
    }

    public void setIsInVaild(String isInVaild) {
        this.isInVaild = isInVaild;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxFreeAmount() {
        return taxFreeAmount;
    }

    public void setTaxFreeAmount(String taxFreeAmount) {
        this.taxFreeAmount = taxFreeAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVerifyTimes() {
        return verifyTimes;
    }

    public void setVerifyTimes(String verifyTimes) {
        this.verifyTimes = verifyTimes;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
