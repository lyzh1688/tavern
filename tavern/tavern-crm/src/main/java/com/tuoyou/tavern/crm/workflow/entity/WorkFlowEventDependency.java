package com.tuoyou.tavern.crm.workflow.entity;

import java.util.Date;

public class WorkFlowEventDependency {
    private String preEvent;

    private String nextEvent;

    private Date createDate;

    public String getPreEvent() {
        return preEvent;
    }

    public void setPreEvent(String preEvent) {
        this.preEvent = preEvent == null ? null : preEvent.trim();
    }

    public String getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(String nextEvent) {
        this.nextEvent = nextEvent == null ? null : nextEvent.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}