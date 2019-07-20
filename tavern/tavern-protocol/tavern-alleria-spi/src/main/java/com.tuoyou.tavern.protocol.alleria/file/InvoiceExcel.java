package com.tuoyou.tavern.protocol.alleria.file;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/21 <br>
 */
public class InvoiceExcel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String invoiceId;
    @ExcelProperty(index = 1)
    private String invoiceCode;
    @ExcelProperty(index = 2)
    private String buyerName;
    @ExcelProperty(index = 3)
    private String buyerTaxCode;
    @ExcelProperty(index = 4)
    private String bankBranch;
    @ExcelProperty(index = 4)
    private String bankAccount;
    @ExcelProperty(index = 5)
    private String address;
    @ExcelProperty(index = 5)
    private String tel;
    @ExcelProperty(index = 6)
    private String invoiceDate;
    @ExcelProperty(index = 7)
    private String productVersion;
    @ExcelProperty(index = 8)
    private String receiptId;
    @ExcelProperty(index = 9)
    private String productName;
    @ExcelProperty(index = 10)
    private String productSpec;
    @ExcelProperty(index = 11)
    private String productUnit;
    @ExcelProperty(index = 12)
    private String productCount;
    @ExcelProperty(index = 13)
    private String productUnitPrice;
    @ExcelProperty(index = 14)
    private String totalPrice;
    @ExcelProperty(index = 15)
    private String taxRate;
    @ExcelProperty(index = 16)
    private String tax;
    @ExcelProperty(index = 17)
    private String taxTypeCode;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTaxCode() {
        return buyerTaxCode;
    }

    public void setBuyerTaxCode(String buyerTaxCode) {
        this.buyerTaxCode = buyerTaxCode;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(String productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTaxTypeCode() {
        return taxTypeCode;
    }

    public void setTaxTypeCode(String taxTypeCode) {
        this.taxTypeCode = taxTypeCode;
    }
}
