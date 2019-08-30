package com.tuoyou.tavern.crm.workflow.entity;

import java.util.Date;

public class WorkFlowEventDependencyHis {
    private String preEvent;

    private String nextEvent;

    private Date createTime;

    private Date finishTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}