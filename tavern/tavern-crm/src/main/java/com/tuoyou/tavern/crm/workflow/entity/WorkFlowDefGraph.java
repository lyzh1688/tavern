package com.tuoyou.tavern.crm.workflow.entity;

public class WorkFlowDefGraph {
    private String graphId;

    private String rootNode;

    private String name;

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId == null ? null : graphId.trim();
    }

    public String getRootNode() {
        return rootNode;
    }

    public void setRootNode(String rootNode) {
        this.rootNode = rootNode == null ? null : rootNode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}