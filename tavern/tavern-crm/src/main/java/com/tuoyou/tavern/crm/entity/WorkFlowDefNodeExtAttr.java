package com.tuoyou.tavern.crm.entity;

public class WorkFlowDefNodeExtAttr {
    private String nodeId;

    private String restDays;

    private String nextNodeTriggerHandler;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getRestDays() {
        return restDays;
    }

    public void setRestDays(String restDays) {
        this.restDays = restDays == null ? null : restDays.trim();
    }

    public String getNextNodeTriggerHandler() {
        return nextNodeTriggerHandler;
    }

    public void setNextNodeTriggerHandler(String nextNodeTriggerHandler) {
        this.nextNodeTriggerHandler = nextNodeTriggerHandler == null ? null : nextNodeTriggerHandler.trim();
    }
}