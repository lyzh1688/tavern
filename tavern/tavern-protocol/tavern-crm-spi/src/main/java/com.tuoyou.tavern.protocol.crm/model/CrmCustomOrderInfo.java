package com.tuoyou.tavern.protocol.crm.model;

public class CrmCustomOrderInfo {
    private String orderId;

    private String customId;

    private String orderDate;

    private Long receivableAmt;

    private Long payableAmt;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate == null ? null : orderDate.trim();
    }

    public Long getReceivableAmt() {
        return receivableAmt;
    }

    public void setReceivableAmt(Long receivableAmt) {
        this.receivableAmt = receivableAmt;
    }

    public Long getPayableAmt() {
        return payableAmt;
    }

    public void setPayableAmt(Long payableAmt) {
        this.payableAmt = payableAmt;
    }
}