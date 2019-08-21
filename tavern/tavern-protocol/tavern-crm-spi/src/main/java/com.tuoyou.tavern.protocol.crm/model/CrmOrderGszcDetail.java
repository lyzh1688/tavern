package com.tuoyou.tavern.protocol.crm.model;

public class CrmOrderGszcDetail {
    private String eventId;

    private String orderId;

    private String absent;

    private String regLocationType;

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

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent == null ? null : absent.trim();
    }

    public String getRegLocationType() {
        return regLocationType;
    }

    public void setRegLocationType(String regLocationType) {
        this.regLocationType = regLocationType == null ? null : regLocationType.trim();
    }
}