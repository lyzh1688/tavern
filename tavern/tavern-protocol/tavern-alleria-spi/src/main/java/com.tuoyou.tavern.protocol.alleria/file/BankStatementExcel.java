package com.tuoyou.tavern.protocol.alleria.file;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/25 <br>
 */
public class BankStatementExcel  extends BaseRowModel {


    @ExcelProperty(index = 0)
    private String customAccntId;
    @ExcelProperty(index = 1)
    private String customAccntName;
    @ExcelProperty(index = 2)
    private String tradeDate;
    @ExcelProperty(index = 3)
    private String debitAmount;
    @ExcelProperty(index = 4)
    private String creditAmount;
    @ExcelProperty(index = 5)
    private String balance;
    @ExcelProperty(index = 6)
    private String coinCode;
    @ExcelProperty(index = 7)
    private String recvAccntId;
    @ExcelProperty(index = 8)
    private String recvAccntName;
    @ExcelProperty(index = 9)
    private String institution;
    @ExcelProperty(index =10)
    private String accountingDate;
    @ExcelProperty(index =11)
    private String abstracts;
    @ExcelProperty(index =12)
    private String remark;
    @ExcelProperty(index = 13)
    private String detailId;
    @ExcelProperty(index = 14)
    private String companySeq;
    @ExcelProperty(index = 15)
    private String certificateType;
    @ExcelProperty(index = 16)
    private String certificateId;

    public String getCustomAccntId() {
        return customAccntId;
    }

    public void setCustomAccntId(String customAccntId) {
        this.customAccntId = customAccntId;
    }

    public String getCustomAccntName() {
        return customAccntName;
    }

    public void setCustomAccntName(String customAccntName) {
        this.customAccntName = customAccntName;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public String getRecvAccntId() {
        return recvAccntId;
    }

    public void setRecvAccntId(String recvAccntId) {
        this.recvAccntId = recvAccntId;
    }

    public String getRecvAccntName() {
        return recvAccntName;
    }

    public void setRecvAccntName(String recvAccntName) {
        this.recvAccntName = recvAccntName;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(String companySeq) {
        this.companySeq = companySeq;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }
}
