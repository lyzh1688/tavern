package com.tuoyou.tavern.crm.workflow.entity;

public class WorkFlowLogMessage {
    private String logId;

    private String operator;

    private String createTime;

    private String message;

    private String eventId;

    private String hasAttatchment;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    public String getHasAttatchment() {
        return hasAttatchment;
    }

    public void setHasAttatchment(String hasAttatchment) {
        this.hasAttatchment = hasAttatchment == null ? null : hasAttatchment.trim();
    }
}