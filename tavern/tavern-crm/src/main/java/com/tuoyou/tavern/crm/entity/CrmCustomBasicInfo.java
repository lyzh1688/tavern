package com.tuoyou.tavern.crm.entity;

import java.util.Date;

public class CrmCustomBasicInfo {
    private String customId;

    private String customAccnt;

    private String customName;

    private String password;

    private String contactName;

    private String contackTel;

    private String isValid;

    private Date updateDate;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public String getCustomAccnt() {
        return customAccnt;
    }

    public void setCustomAccnt(String customAccnt) {
        this.customAccnt = customAccnt == null ? null : customAccnt.trim();
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName == null ? null : customName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContackTel() {
        return contackTel;
    }

    public void setContackTel(String contackTel) {
        this.contackTel = contackTel == null ? null : contackTel.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}