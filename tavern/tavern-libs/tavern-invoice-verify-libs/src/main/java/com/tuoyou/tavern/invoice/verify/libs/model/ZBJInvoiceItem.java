package com.tuoyou.tavern.invoice.verify.libs.model;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public class ZBJInvoiceItem {
    String name;    //名称
    String spec;    //规格
    String unit;    //单位
    String amount;  //数量
    String priceUnit;   //单价
    String priceSum;    //金额
    String taxRate;     //税率
    String taxSum;      //税额

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(String priceSum) {
        this.priceSum = priceSum;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(String taxSum) {
        this.taxSum = taxSum;
    }
}
