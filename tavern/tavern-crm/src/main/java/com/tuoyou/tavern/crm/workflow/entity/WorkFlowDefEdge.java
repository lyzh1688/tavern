package com.tuoyou.tavern.crm.workflow.entity;

public class WorkFlowDefEdge {
    private String edgeId;

    private String graphId;

    private String sourceNode;

    private String targetNode;

    private String tag;

    public String getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(String edgeId) {
        this.edgeId = edgeId == null ? null : edgeId.trim();
    }

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId == null ? null : graphId.trim();
    }

    public String getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(String sourceNode) {
        this.sourceNode = sourceNode == null ? null : sourceNode.trim();
    }

    public String getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(String targetNode) {
        this.targetNode = targetNode == null ? null : targetNode.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}