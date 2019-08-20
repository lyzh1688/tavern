package com.tuoyou.tavern.crm.entity;

import java.util.Date;

public class CrmCustomBasicInfo {
    private String customId;

    private String weixinAccnt;

    private String wangwangAccnt;

    private String contactPerson;

    private String contactNumber;

    private String corporation;

    private String corporationNumber;

    private String customLevel;

    private String customName;

    private Date updateDate;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public String getWeixinAccnt() {
        return weixinAccnt;
    }

    public void setWeixinAccnt(String weixinAccnt) {
        this.weixinAccnt = weixinAccnt == null ? null : weixinAccnt.trim();
    }

    public String getWangwangAccnt() {
        return wangwangAccnt;
    }

    public void setWangwangAccnt(String wangwangAccnt) {
        this.wangwangAccnt = wangwangAccnt == null ? null : wangwangAccnt.trim();
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation == null ? null : corporation.trim();
    }

    public String getCorporationNumber() {
        return corporationNumber;
    }

    public void setCorporationNumber(String corporationNumber) {
        this.corporationNumber = corporationNumber == null ? null : corporationNumber.trim();
    }

    public String getCustomLevel() {
        return customLevel;
    }

    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel == null ? null : customLevel.trim();
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName == null ? null : customName.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}