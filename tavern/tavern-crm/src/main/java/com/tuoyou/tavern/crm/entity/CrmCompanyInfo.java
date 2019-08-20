package com.tuoyou.tavern.crm.entity;

import java.util.Date;

public class CrmCompanyInfo {
    private String companyId;

    private String taxType;

    private String city;

    private String district;

    private String financeDiskType;

    private Long taxRate;

    private Date updateDate;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType == null ? null : taxType.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getFinanceDiskType() {
        return financeDiskType;
    }

    public void setFinanceDiskType(String financeDiskType) {
        this.financeDiskType = financeDiskType == null ? null : financeDiskType.trim();
    }

    public Long getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Long taxRate) {
        this.taxRate = taxRate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}