package com.tuoyou.tavern.crm.entity;

public class CrmOrderBusinessRel {
    private String eventId;

    private String orderId;

    private String businessId;

    private String companyId;

    private String ownerId;

    private String needThirdParty;

    private String thirdPartyId;

    private Long thirdPartyFee;

    private String businessTag;

    private String preEventId;

    private String remark;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getNeedThirdParty() {
        return needThirdParty;
    }

    public void setNeedThirdParty(String needThirdParty) {
        this.needThirdParty = needThirdParty == null ? null : needThirdParty.trim();
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }

    public Long getThirdPartyFee() {
        return thirdPartyFee;
    }

    public void setThirdPartyFee(Long thirdPartyFee) {
        this.thirdPartyFee = thirdPartyFee;
    }

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag == null ? null : businessTag.trim();
    }

    public String getPreEventId() {
        return preEventId;
    }

    public void setPreEventId(String preEventId) {
        this.preEventId = preEventId == null ? null : preEventId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}