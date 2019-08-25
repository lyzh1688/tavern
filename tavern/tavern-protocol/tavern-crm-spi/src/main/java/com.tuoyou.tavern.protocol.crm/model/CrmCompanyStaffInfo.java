package com.tuoyou.tavern.protocol.crm.model;

public class CrmCompanyStaffInfo {
    private String staffId;

    private String idCard;

    private String contactNumber;

    private String needSocialIns;

    private String needHabitationIns;

    private String needSocialServer;

    private String needHabitationServer;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getNeedSocialIns() {
        return needSocialIns;
    }

    public void setNeedSocialIns(String needSocialIns) {
        this.needSocialIns = needSocialIns == null ? null : needSocialIns.trim();
    }

    public String getNeedHabitationIns() {
        return needHabitationIns;
    }

    public void setNeedHabitationIns(String needHabitationIns) {
        this.needHabitationIns = needHabitationIns == null ? null : needHabitationIns.trim();
    }

    public String getNeedSocialServer() {
        return needSocialServer;
    }

    public void setNeedSocialServer(String needSocialServer) {
        this.needSocialServer = needSocialServer == null ? null : needSocialServer.trim();
    }

    public String getNeedHabitationServer() {
        return needHabitationServer;
    }

    public void setNeedHabitationServer(String needHabitationServer) {
        this.needHabitationServer = needHabitationServer == null ? null : needHabitationServer.trim();
    }
}